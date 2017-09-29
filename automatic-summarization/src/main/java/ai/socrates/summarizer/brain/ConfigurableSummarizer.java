package ai.socrates.summarizer.brain;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.jcas.JCas;

import ai.socrates.summarizer.types.nlp.SummarizationMethodENUM;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;



public class ConfigurableSummarizer {
	
	public static Map<SentenceAnnotation, Double> summarize(JCas jcas, SummarizationMethodENUM approach) {
		Map<SentenceAnnotation, Double> ret= new HashMap<SentenceAnnotation, Double>();
		switch(approach) {
			case FirstN:
				break;
		}
		return new HashMap<SentenceAnnotation, Double>();
		
	}


}
