package ai.socrates.summarizer.brain;


import java.util.LinkedHashMap;
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
	Map<ISummarizer, Float> summarizers=null;
	
	public AggregateSummarizer(Map<ISummarizer, Float> summarizers){
		this.summarizers=summarizers;
	}
	
	
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
			
			if (summarizers==null){
				logger.error("Summarizers not initialized");
				return ret;
			}
			
			float cummulativeWeight=0;
			for(Float sWeight: summarizers.values()){
				cummulativeWeight+=sWeight;
			}
			if (cummulativeWeight==0){
				logger.error("Cummulative weight of summarizers is 0");
				return ret;
			}
			
			List<String> keywords=null;
			Map<SentencesChunkAnnotation, Float> cummulativeScores= new LinkedHashMap<>();
			for(ISummarizer summarizer:summarizers.keySet()){
				float summarizerWeightFactor=summarizers.get(summarizer)/cummulativeWeight;
				Map<SentencesChunkAnnotation, Float> summarizerScores=summarizer.summarize(jcas, jcasQ, titleStrength);
				for(SentencesChunkAnnotation sca: summarizerScores.keySet()){
					if (cummulativeScores.containsKey(sca)){
						if (!summarizer.isBoosterOnly() || cummulativeScores.get(sca)>0)
							cummulativeScores.put(sca, cummulativeScores.get(sca)+summarizerScores.get(sca)*summarizerWeightFactor);
					}
					else{
						if (!summarizer.isBoosterOnly()){
							cummulativeScores.put(sca, summarizerScores.get(sca)*summarizerWeightFactor);
						}
					}
				}
				
				if (keywords==null)
					keywords=summarizer.getKeywords();
			}
			
			cummulativeScores= Misc.sortByValue(cummulativeScores);
			
			List<SentencesChunkAnnotation> summaryChunkAnnotations;
			if (summarySize.endsWith("%")){
				Double percent=Double.parseDouble(summarySize.replace("%", ""));
				logger.info("Going to create " + percent + "% summary");
				summaryChunkAnnotations=Misc.getSummary(cummulativeScores, jcas, percent);
			}
			else{
				Integer cnt=Integer.parseInt(summarySize);
				logger.info("Going to create " + cnt + " sentences summary");
				summaryChunkAnnotations=Misc.getSummary(cummulativeScores, jcas,cnt);
			}
			
			float bestScore=0;
			float cummulativeScore=0;
			if (summaryChunkAnnotations.size()>0){
				bestScore=cummulativeScores.get(summaryChunkAnnotations.get(0));
				for (SentencesChunkAnnotation sca:summaryChunkAnnotations)
					cummulativeScore+=cummulativeScores.get(sca);
			}
			
			if (orderOfSentences.equals("byPosition")){
				// sort summarySentences by position
				logger.info("Sorting by position");
				Misc.sortByPosition(summaryChunkAnnotations);
				
			}
			
			ret= new SummarizedDocument();
			ret.setKeywords(keywords);
			ret.setId(article.getId());
			ret.setTitle(article.getTitle());
			ret.setBestSentenceScore(bestScore);
			ret.setCummulativeScore(cummulativeScore);
			StringBuilder summaryBuilder=new StringBuilder();
			for(SentencesChunkAnnotation s:summaryChunkAnnotations) {
				summaryBuilder.append(s.getCoveredText());
				summaryBuilder.append(" ");
			}
			String summary=summaryBuilder.toString().trim();
			if (summary.isEmpty())
				summary="-";
			ret.setSummary(summary);
			
			
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
		logger.debug("Releasing NLP resources...");
		if (jcas!=null){
			coreNLP.releaseCas(jcas);
			logger.debug("Done.");
		}
		else
			logger.info("nlpResponse is null");
	}
	

}
