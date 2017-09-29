package ai.socrates.summarizer.brain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;
import ai.socrates.summarizer.types.uima.TokenAnnotation;
import ai.socrates.summarizer.utils.Misc;

public class RelevanceSummarizer implements ISummarizer{
	private static final Logger logger =LoggerFactory.getLogger(RelevanceSummarizer.class);
	private List<String> keywords= new ArrayList<>();

	@Override
	public Map<SentencesChunkAnnotation, Double> summarize(JCas jcas, JCas jcasQ, int skipCount) {
		Map<SentencesChunkAnnotation, Double> ret= new LinkedHashMap<SentencesChunkAnnotation, Double>();
		
		
		Map<String, Integer> vectorToCompare=new HashMap<String, Integer>();
		AnnotationIndex<Annotation> tIndex;
		if (jcasQ==null) // get document vector 
			tIndex=jcas.getAnnotationIndex(TokenAnnotation.type);
		else // get query vector
			tIndex=jcasQ.getAnnotationIndex(TokenAnnotation.type);
		FSIterator<Annotation> tIter = tIndex.iterator();
		while (tIter.hasNext()) {
			TokenAnnotation t = (TokenAnnotation)tIter.next();
			if (isTokenImportant(t)){
				String lemma=t.getLemma().toLowerCase();
				if (vectorToCompare.containsKey(lemma)){
					vectorToCompare.put(lemma, vectorToCompare.get(lemma)+1);
				}
				else{
					vectorToCompare.put(lemma, 1);
				}
			}
		}
		logger.info("Vector to compare size: "  + vectorToCompare.size());
		Map<String, Integer> vectorToCompareSorted= Misc.sortByValue(vectorToCompare);
		int cnt=1;
		
		for(Entry<String, Integer> tEntry : vectorToCompareSorted.entrySet()){
			logger.info(tEntry.getKey() + " : " + tEntry.getValue());
			keywords.add(tEntry.getKey());
			if (cnt++>=10)
				break;
		}
		
		AnnotationIndex<Annotation> sIndex = jcas.getAnnotationIndex(SentencesChunkAnnotation.type);
		FSIterator<Annotation> sIter = sIndex.iterator();
		double maxSimilarity=0;
		while (sIter.hasNext()) {
			SentencesChunkAnnotation s = (SentencesChunkAnnotation)sIter.next();
			if (skipCount>0){
				skipCount=skipCount-s.getSentencesCount();
				continue;
			}
			Map<String, Integer> sentenceVector=new HashMap<String, Integer>();
			List<TokenAnnotation> tokens=JCasUtil.selectCovered(TokenAnnotation.class, s);
			for(TokenAnnotation t:tokens){
				if (isTokenImportant(t)){
					String lemma=t.getLemma().toLowerCase();
					if (sentenceVector.containsKey(lemma)){
						sentenceVector.put(lemma, sentenceVector.get(lemma)+1);
					}
					else{
						sentenceVector.put(lemma, 1);
					}
				}
			}
			double similarity=0;
			if (!sentenceVector.isEmpty())
				similarity=CosineSimilarity.similarity(sentenceVector, vectorToCompare);
			else
				logger.warn("Vector for sentence chunk '" + s.getCoveredText() + "' is empty" );
//			logger.info(""+similarity);
//			if (similarity>maxSimilarity){
//				logger.info("Leader: " + s.getCoveredText());
//				maxSimilarity=similarity;
//			}
			ret.put(s, similarity);
		}
		
		return Misc.sortByValue(ret);
	}
	
	public List<String> getKeywords() {
		return keywords;
	}

	private boolean isTokenImportant(TokenAnnotation t){
		boolean ret=false;
		if (t.getTokenType().matches("[A-Z]*")){ // eliminate various punctaitons
			if (!t.getIsStopWord()){
				ret=true;
			}
		}
		return ret;
	}

}
