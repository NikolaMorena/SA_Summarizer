package ai.socrates.summarizer.brain;

import java.util.Map;

import org.apache.uima.jcas.JCas;

import ai.socrates.summarizer.types.nlp.SummarizationMethodENUM;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;

public interface ISummarizer {
	public Map<SentencesChunkAnnotation, Double> summarize(JCas jcas, JCas jcasQ, int skipCount);
}
