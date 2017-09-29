package ai.socrates.summarizer.service;

import java.io.File;
import java.util.Collection;

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
import ai.socrates.summarizer.types.nlp.Article;
import ai.socrates.summarizer.types.nlp.MultiDocumentSummary;
import ai.socrates.summarizer.types.nlp.SummarizedDocument;
import ai.socrates.summarizer.utils.PropertyHelper;
import ai.socrates.summarizer.utils.SolrClient;

@RestController
public class SummarizerController {
	
	private static final Logger logger =LoggerFactory.getLogger(SummarizerController.class);
	
	@RequestMapping(value="/summarize", method = {RequestMethod.GET}, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> summarizeAllArticles(
			@RequestParam(value="query", defaultValue="") String query,
			@RequestParam(value="summary_size", defaultValue="5") String summarySize,
			@RequestParam(value="title_strength", defaultValue="1") int titleStrength,
			@RequestParam(value="boost_by_position", defaultValue="false") boolean boostByOrder,
			@RequestParam(value="order_of_sentences", defaultValue="byPosition") String orderOfSentences,
			@RequestParam(value="client_id", defaultValue="2") int clientId
			){
		logger.info("GET request received");
		logger.info("Summary size is " + summarySize);
		try{
			logger.info("GET request received, search query is " + query);
			SolrDocumentList solrDocumentList = SolrClient.getRelevantSections(query, clientId);
			MultiDocumentSummary mds= new MultiDocumentSummary();
			StringBuilder mdSummary= new StringBuilder();
			boolean firstSummary=true;
			for(SolrDocument solrd:solrDocumentList){
				String sectionName=solrd.get("section_name").toString();
				logger.info("Processing section " + sectionName);
				Collection<Object> content=solrd.getFieldValues("content");
				StringBuilder sb= new StringBuilder();
				String previousContent="Mockup";
				for(Object cnt:content){
					String currentContent=cnt.toString().trim();
					if (!currentContent.isEmpty()){
						if (!previousContent.endsWith(".") && Character.isUpperCase(currentContent.charAt(0)))
							logger.info("Skipping " + previousContent);
						else{
							logger.info("Adding: " + previousContent);
							sb.append(previousContent);
							sb.append(" ");
						}
						previousContent=currentContent;
					}
				}
				sb.append(previousContent);
				logger.info("Adding: " + previousContent);
				String articleText=sb.toString();
				logger.info(articleText);
				String articleId=solrd.get("id").toString();
				Article article= new Article();
				article.setId(articleId);
				article.setTitle(sectionName);
				article.setText(articleText);
				try{
					AggregateSummarizer summarizer= new AggregateSummarizer();
					SummarizedDocument sd=summarizer.getSummary(article, query, summarySize, titleStrength, boostByOrder, orderOfSentences);
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
}
