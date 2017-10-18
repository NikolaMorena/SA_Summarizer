package ai.socrates.summarizer.brain;

import java.util.List;
import java.util.Map;

import org.apache.uima.jcas.JCas;

import ai.socrates.summarizer.types.nlp.SummarizationMethodENUM;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;

public interface ISummarizer {
	public Map<SentencesChunkAnnotation, Float> summarize(JCas jcas, JCas jcasQ, int skipCount);
	public List<String> getKeywords();
	public Boolean isBoosterOnly();
}
