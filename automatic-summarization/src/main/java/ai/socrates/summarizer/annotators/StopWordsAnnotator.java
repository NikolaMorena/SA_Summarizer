package ai.socrates.summarizer.annotators;

import java.io.InputStream;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.uima.TokenAnnotation;
import ai.socrates.summarizer.utils.Misc;





public class StopWordsAnnotator extends JCasAnnotator_ImplBase {

	private static final Logger logger =LoggerFactory.getLogger(StopWordsAnnotator.class);
	List<String> stopWordsList;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(aContext);
		InputStream iscw = getClass().getClassLoader().getResourceAsStream("dictionaries/StopWords.txt");
		try {
			stopWordsList = Misc.readList(iscw, true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("StopWordsAnnotator initialized");
	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		logger.debug("StopWordsAnnotator starts processing");
		AnnotationIndex<Annotation> cIndex = jcas.getAnnotationIndex(TokenAnnotation.type);
		FSIterator<Annotation> cIter = cIndex.iterator();
		while (cIter.hasNext()) {
			TokenAnnotation tokenAnnotation = (TokenAnnotation) cIter.next();
			String token = tokenAnnotation.getCoveredText();
			String lemma = tokenAnnotation.getLemma();
			boolean isStopWord = stopWordsList.contains(token.toLowerCase());
			isStopWord= isStopWord || stopWordsList.contains(lemma.toLowerCase());
			tokenAnnotation.setIsStopWord(isStopWord);
		}

		logger.debug("StopWordsAnnotator processing finished");
	}

}
