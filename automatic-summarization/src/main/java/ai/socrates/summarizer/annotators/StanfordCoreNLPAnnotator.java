package ai.socrates.summarizer.annotators;
import java.util.Properties;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.uima.DateAnnotation;
import ai.socrates.summarizer.types.uima.DurationAnnotation;
import ai.socrates.summarizer.types.uima.MoneyAnnotation;
import ai.socrates.summarizer.types.uima.NamedEntityCandidateAnnotation;
import ai.socrates.summarizer.types.uima.PercentAnnotation;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.TokenAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetBeginAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;



public class StanfordCoreNLPAnnotator extends JCasAnnotator_ImplBase {
	private static final Logger logger =LoggerFactory.getLogger(StanfordCoreNLPAnnotator.class);
	private StanfordCoreNLP pipeline;
	private boolean useNER=false;


	@Override
	public void reconfigure() throws org.apache.uima.resource.ResourceConfigurationException ,org.apache.uima.resource.ResourceInitializationException	{

	}

	@Override
	public void initialize(org.apache.uima.UimaContext aContext) throws org.apache.uima.resource.ResourceInitializationException {
		super.initialize(aContext);		
		try {
			Properties props = new Properties();
			props.setProperty("annotators",	"tokenize, ssplit, pos, lemma");
			pipeline = new StanfordCoreNLP(props);

		} 
		catch (Exception e) {
			logger.error("Initialization failed. " + e.getMessage());
		}
	}


	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		logger.info("Stanford CoreNLP started");
		Annotation annotation = new Annotation(jcas.getDocumentText());
		pipeline.annotate(annotation);
		for (CoreMap tokenAnn : annotation.get(TokensAnnotation.class)) {
			// create the token annotation
			int begin = tokenAnn.get(CharacterOffsetBeginAnnotation.class);
			int end = tokenAnn.get(CharacterOffsetEndAnnotation.class);
			String pos = tokenAnn.get(PartOfSpeechAnnotation.class);
			String lemma = tokenAnn.get(LemmaAnnotation.class);
			TokenAnnotation token = new TokenAnnotation(jcas, begin, end);
			token.setTokenType(pos);
			token.setLemma(lemma);
			token.addToIndexes();
			if (lemma.toLowerCase().equals("datum")){
				logger.info(token.getCoveredText() + "->" + lemma);
			}
		}


		int counter=1;
		for(CoreMap sentence:annotation.get(SentencesAnnotation.class)){
			int begin = sentence.get(CharacterOffsetBeginAnnotation.class);
			int end = sentence.get(CharacterOffsetEndAnnotation.class);
			SentenceAnnotation sa= new SentenceAnnotation(jcas);
			sa.setBegin(begin);
			sa.setEnd(end);
			sa.setSeqNumber(counter++);
			sa.setSelectedForSummary(false);
			sa.addToIndexes();
		}

		// Named Entities
		if (useNER){
			logger.info("NER started");
			String lastNETag = "O";
			int lastNEBegin = -1;
			int lastNEEnd = -1;
			for (CoreMap tokenAnn : annotation.get(TokensAnnotation.class)) {
				int begin = tokenAnn.get(CharacterOffsetBeginAnnotation.class);
				int end = tokenAnn.get(CharacterOffsetEndAnnotation.class);
				String neTag = tokenAnn.get(NamedEntityTagAnnotation.class);
				if (neTag.equals("O") && !lastNETag.equals("O")) {
					addNamedEntityCandidate(jcas, lastNEBegin, lastNEEnd, lastNETag);
					//System.out.println(inputText.substring(lastNEBegin, lastNEEnd) + "-" + lastNETag);
				} else {
					if (lastNETag.equals("O")) {
						lastNEBegin = begin;
					} else if (lastNETag.equals(neTag)) {
						// do nothing - begin was already set
					} else {
						addNamedEntityCandidate(jcas, lastNEBegin, lastNEEnd, lastNETag);
						//System.out.println(inputText.substring(lastNEBegin, lastNEEnd) + "-" + lastNETag);
						lastNEBegin = begin;
					}
					lastNEEnd = end;
				}
				lastNETag = neTag;
			}
			if (!lastNETag.equals("O")) {
				addNamedEntityCandidate(jcas, lastNEBegin, lastNEEnd, lastNETag);
				//System.out.println(inputText.substring(lastNEBegin, lastNEEnd) + "-" + lastNETag);
			}
		}

	}

	private void addNamedEntityCandidate(JCas jcas, int begin, int end, String entityType){
		NamedEntityCandidateAnnotation nec = new NamedEntityCandidateAnnotation(jcas, begin, end);
		nec.setEntityType(entityType);
		nec.setDetectionSource("ML-Stanford");
		nec.addToIndexes();

		if (entityType.equals("MONEY")){
			MoneyAnnotation m = new MoneyAnnotation(jcas, begin, end);
			m.addToIndexes();
			logger.debug("Money detected: " + m.getCoveredText());
		}
		if (entityType.equals("PERCENT")){
			PercentAnnotation p = new PercentAnnotation(jcas, begin, end);
			p.addToIndexes();
		}
		if (entityType.equals("DURATION")){
			DurationAnnotation d = new DurationAnnotation(jcas, begin, end);
			d.addToIndexes();
		}
		if (entityType.equals("DATE")){
			String text=jcas.getDocumentText().substring(begin, end);
			int toIndex=text.indexOf(" to ");
			if (toIndex==-1){
				DateAnnotation d = new DateAnnotation(jcas, begin, end);
				d.addToIndexes();
			}
			else{
				if (toIndex>0){
					DateAnnotation d1 = new DateAnnotation(jcas, begin, begin+toIndex);
					d1.addToIndexes();
				}
				if (begin+toIndex+4<end){
					DateAnnotation d2 = new DateAnnotation(jcas, begin+toIndex+4, end);
					d2.addToIndexes();
				}
			}
		}
	}

	



}
