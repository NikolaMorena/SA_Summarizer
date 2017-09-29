package ai.socrates.summarizer.brain;


import java.util.List;
import java.util.Map;


import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.nlp.Article;
import ai.socrates.summarizer.types.nlp.SummarizedDocument;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;
import ai.socrates.summarizer.utils.Misc;


public class AggregateSummarizer {
	private static final Logger logger =LoggerFactory.getLogger(AggregateSummarizer.class);
	CoreNLPUIMA coreNLP;
	
	public SummarizedDocument getSummary(Article article, String query, String summarySize, int titleStrength, boolean boostByOrder, String orderOfSentences){
		SummarizedDocument ret = null;
		JCas jcas=null;
		JCas jcasQ=null;
		try{
			StringBuilder ttBuilder=new StringBuilder();
			for(int i=0;i<titleStrength;i++){
				ttBuilder.append(article.getTitle() + ". ");
			}
			ttBuilder.append(article.getText());
			String text= ttBuilder.toString();
			jcas=doNLP(text);
			if (!query.isEmpty())
				jcasQ=doNLP(query);
			RelevanceSummarizer rm= new RelevanceSummarizer();
			Map<SentencesChunkAnnotation, Double> relevanceSummarizerScores=rm.summarize(jcas, jcasQ, titleStrength);
			if (boostByOrder){
				PositionSummarizer positionSummarizer= new PositionSummarizer();
				Map<SentencesChunkAnnotation, Double> positionSummarizerScores= positionSummarizer.summarize(jcas, jcasQ, titleStrength);
				for(SentencesChunkAnnotation sa:relevanceSummarizerScores.keySet()){
					Double relevanceScore=relevanceSummarizerScores.get(sa);
					Double positionScore=positionSummarizerScores.get(sa);
					Double updatedScore=(relevanceScore*2+positionScore)/3f;
					relevanceSummarizerScores.put(sa, updatedScore);
				}
				relevanceSummarizerScores=Misc.sortByValue(relevanceSummarizerScores);
			}
			
			List<SentencesChunkAnnotation> summarySentences;
			if (summarySize.endsWith("%")){
				Double percent=Double.parseDouble(summarySize.replace("%", ""));
				logger.info("Going to create " + percent + "% summary");
				summarySentences=Misc.getSummary(relevanceSummarizerScores, jcas, percent);
			}
			else{
				Integer cnt=Integer.parseInt(summarySize);
				logger.info("Going to create " + cnt + " sentences summary");
				summarySentences=Misc.getSummary(relevanceSummarizerScores, jcas,cnt);
			}
			if (orderOfSentences.equals("byPosition")){
				// sort summarySentences by position
				logger.info("Sorting by position");
				Misc.sortByPosition(summarySentences);	
			}
			List<String> keywords= rm.getKeywords();
			ret= new SummarizedDocument();
			ret.setKeywords(keywords);
			ret.setId(article.getId());
			ret.setTitle(article.getTitle());
			StringBuilder summaryBuilder=new StringBuilder();
			for(SentencesChunkAnnotation s:summarySentences) {
				summaryBuilder.append(s.getCoveredText());
				summaryBuilder.append("&nbsp;");
			}
			ret.setSummary(summaryBuilder.toString());
			
			// create text with selected sentences in bold
			ttBuilder=new StringBuilder();
			AnnotationIndex<Annotation> sIndex = jcas.getAnnotationIndex(SentenceAnnotation.type);
			FSIterator<Annotation> sIter = sIndex.iterator();
			int skipCount=titleStrength;
			while (sIter.hasNext()) {
				SentenceAnnotation s = (SentenceAnnotation)sIter.next();
				if (skipCount>0){
					skipCount--;
					continue;
				}
				if (s.getSelectedForSummary()){
					ttBuilder.append("<B>");
					ttBuilder.append(s.getCoveredText());
					ttBuilder.append("&nbsp;");
					ttBuilder.append("</B>");
				}
				else{
					ttBuilder.append(s.getCoveredText());
					ttBuilder.append("&nbsp;");
				}
			}
			ret.setText(ttBuilder.toString());
		}
		catch(Exception ex){
			logger.error(ex.getMessage());
		}
		finally{
			releaseNLPResource(jcas);
			releaseNLPResource(jcasQ);
		}
		return ret;
	}
	
	private JCas doNLP(String text){
		if (!CoreNLPUIMA.isInitialized()){
			logger.error("NLP component is not properly initialized");
			return null;
		}
		coreNLP= new CoreNLPUIMA();
		JCas nlpResponse= coreNLP.doNLP(text);
		return nlpResponse;
	}
	
	private void releaseNLPResource(JCas jcas){
		logger.info("Releasing NLP resources...");
		if (jcas!=null){
			coreNLP.releaseCas(jcas);
			logger.info("Done.");
		}
		else
			logger.info("nlpResponse is null");
	}
	
	

}
