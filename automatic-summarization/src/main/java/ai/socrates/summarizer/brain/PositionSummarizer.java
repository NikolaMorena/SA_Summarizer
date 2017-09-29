package ai.socrates.summarizer.brain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;

public class PositionSummarizer implements ISummarizer {
	private static final Logger logger =LoggerFactory.getLogger(PositionSummarizer.class);

	@Override
	public Map<SentencesChunkAnnotation, Double> summarize(JCas jcas, JCas jcasQ, int skipCount) {
		Map<SentencesChunkAnnotation, Double> ret= new LinkedHashMap<SentencesChunkAnnotation, Double>();
		AnnotationIndex<Annotation> sIndex = jcas.getAnnotationIndex(SentencesChunkAnnotation.type);
		int numberOfSentenceChunks=sIndex.size();
		FSIterator<Annotation> sIter = sIndex.iterator();
		int cnt=0;
		while (sIter.hasNext()) {
			SentencesChunkAnnotation s = (SentencesChunkAnnotation)sIter.next();
			if (skipCount>0){
				skipCount--;
				continue;
			}
			Double score=1-cnt/(1.0*numberOfSentenceChunks);
			ret.put(s, score);
			logger.info(""+score);
			cnt++;
		}
		return ret;
	}

}
