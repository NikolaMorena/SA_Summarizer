package ai.socrates.summarizer.service;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.socrates.summarizer.brain.AggregateSummarizer;
import ai.socrates.summarizer.brain.ISummarizer;
import ai.socrates.summarizer.brain.PositionSummarizer;
import ai.socrates.summarizer.brain.RelevanceSummarizer;
import ai.socrates.summarizer.types.nlp.Article;
import ai.socrates.summarizer.types.nlp.MultiDocumentSummary;
import ai.socrates.summarizer.types.nlp.SummarizedDocument;
import ai.socrates.summarizer.utils.Misc;
import ai.socrates.summarizer.utils.PropertyHelper;
import ai.socrates.summarizer.utils.SolrClient;

@RestController
public class SummarizerController {
	
	private static final Logger logger =LoggerFactory.getLogger(SummarizerController.class);
	
	@RequestMapping(value="/summarize", method = {RequestMethod.GET}, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> summarizeAllArticles(
			@RequestParam(value="query", defaultValue="") String userInput,
			@RequestParam(value="max_summary_size", defaultValue="5") int maxSummarySize,
			@RequestParam(value="min_relevancy_score", defaultValue="0.4") float minRelevancyScore,
			@RequestParam(value="title_strength", defaultValue="1") int titleStrength,
			@RequestParam(value="boost_by_position", defaultValue="false") boolean boostByOrder,
			@RequestParam(value="order_of_sentences", defaultValue="byPosition") String orderOfSentences,
			@RequestParam(value="client_id", defaultValue="2") int clientId
			){
		logger.info("GET request received, user input is " + userInput);
		logger.info("Max Summary size is " + maxSummarySize);
		try{
			Map<ISummarizer, Float> summarizers= getSummarizers(boostByOrder);
			String query=Misc.getSearchQuery(userInput);
			logger.info("Search query is " + query);
			SolrDocumentList solrDocumentList = SolrClient.getRelevantSections(query, clientId);
			MultiDocumentSummary mds= new MultiDocumentSummary();
			StringBuilder mdSummary= new StringBuilder();
			boolean firstSummary=true;
			for(SolrDocument solrd:solrDocumentList){
				String sectionName=solrd.get("section_name").toString();
				logger.info("Processing section " + sectionName);
				Collection<Object> content=solrd.getFieldValues("content");
				String sectionText=getSectionTextFromContent(content);
				String articleId=solrd.get("id").toString();
				Article article= new Article();
				article.setId(articleId);
				article.setTitle(sectionName);
				article.setText(sectionText);
				try{
					AggregateSummarizer summarizer= new AggregateSummarizer(summarizers);
					SummarizedDocument sd=summarizer.getSummary(article, query, maxSummarySize, minRelevancyScore, titleStrength, boostByOrder, orderOfSentences);
					mds.getSummarizedDocuments().add(sd);
					if (!sd.getSummary().isEmpty())
						if (firstSummary){ // first summary only
							mdSummary.append(sd.getSummary()); //.append("<BR>");
							firstSummary=false;
						}
					else
						logger.warn("Summary for section '" + sectionName + "' is empty");
				}
				catch(Exception ex){
					logger.error("Failed to process doc " + articleId + ". Error is " + ex.getMessage());		
				}
			}
			mds.setSummaryText(mdSummary.toString());
			return new ResponseEntity<MultiDocumentSummary>(mds, HttpStatus.OK);
		}
		catch(Exception ex){
			logger.error(ex.getMessage());
			SolrDocumentList errDocs = new SolrDocumentList() ;
			SolrDocument errDoc = new SolrDocument() ;
			errDoc.addField("error", ex.getMessage());
			errDocs.add(errDoc);
			return new ResponseEntity<SolrDocumentList>(errDocs, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value="/summarize-text", method = {RequestMethod.GET}, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> summarizeText(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="query", defaultValue="") String query,
			@RequestParam(value="max_summary_size", defaultValue="5") int maxSummarySize,
			@RequestParam(value="min_relevancy_score", defaultValue="0.4") float minRelevancyScore,
			@RequestParam(value="title_strength", defaultValue="1") int titleStrength,
			@RequestParam(value="boost_by_position", defaultValue="false") boolean boostByOrder,
			@RequestParam(value="order_of_sentences", defaultValue="byPosition") String orderOfSentences,
			@RequestParam(value="client_id", defaultValue="2") int clientId
			){
		logger.info("GET request received, text is " + text);
		logger.info("Max Summary size is " + maxSummarySize);
		String ret="";
		try{
			Map<ISummarizer, Float> summarizers= getSummarizers(boostByOrder);
;
			Article article= new Article();
			article.setId("?");
			article.setTitle("?");
			article.setText(text);
			try{
				AggregateSummarizer summarizer= new AggregateSummarizer(summarizers);
				SummarizedDocument sd=summarizer.getSummary(article, query, maxSummarySize, minRelevancyScore, titleStrength, boostByOrder, orderOfSentences);
				if (sd.getSummary().isEmpty())
					logger.warn("Summary is empty");
				ret=sd.getSummary();
			}
			catch(Exception ex){
				logger.error("Failed to create the summary. Error is {}", ex.getMessage());		
			}

			return new ResponseEntity<String>(ret, HttpStatus.OK);
		}
		catch(Exception ex){
			logger.error(ex.getMessage());
			SolrDocumentList errDocs = new SolrDocumentList() ;
			SolrDocument errDoc = new SolrDocument() ;
			errDoc.addField("error", ex.getMessage());
			errDocs.add(errDoc);
			return new ResponseEntity<SolrDocumentList>(errDocs, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	private String getSectionTextFromContent(Collection<Object> content) {
		StringBuilder sb= new StringBuilder();
		String previousContent="Mockup";
		for(Object cnt:content){
			String currentContent=cnt.toString().trim();
			if (!currentContent.isEmpty()){
				if (!previousContent.endsWith(".") && Character.isUpperCase(currentContent.charAt(0)))
					logger.debug("Skipping " + previousContent);
				else{
					logger.debug("Adding: " + previousContent);
					sb.append(previousContent);
					sb.append(" ");
				}
				previousContent=currentContent;
			}
		}
		sb.append(previousContent);
		logger.info("Adding: " + previousContent);
		String ret=sb.toString();
		logger.info(ret);
		return ret; 
	}

	private Map<ISummarizer, Float> getSummarizers(boolean boostByOrder){
		Map<ISummarizer, Float> summarizers= new LinkedHashMap<>();
		summarizers.put(new RelevanceSummarizer(), 2f);
		if (boostByOrder)
			summarizers.put(new PositionSummarizer(), 1f);
		return summarizers;
	}
}
