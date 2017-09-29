package ai.socrates.summarizer.annotators;


import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import ai.socrates.summarizer.types.uima.TokenAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetBeginAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;



public class StanfordTokenizer extends JCasAnnotator_ImplBase {

	private Logger logger;
	private StanfordCoreNLP pipeline;
	private boolean useNER=false;


	@Override
	public void reconfigure() throws org.apache.uima.resource.ResourceConfigurationException ,org.apache.uima.resource.ResourceInitializationException	{

	}

	@Override
	public void initialize(org.apache.uima.UimaContext aContext) throws org.apache.uima.resource.ResourceInitializationException {
		super.initialize(aContext);

		try {
			logger = aContext.getLogger();
			Properties props = new Properties();
			props.setProperty("annotators",	"tokenize");
			pipeline = new StanfordCoreNLP(props);

		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, "Initialization failed. " + e.getMessage());
		}
	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		logger.log(Level.FINER, "Stanford Tokenizer started");
		Annotation annotation = new Annotation(jcas.getDocumentText());
		pipeline.annotate(annotation);
		for (CoreMap tokenAnn : annotation.get(TokensAnnotation.class)) {
			// create the token annotation
			int begin = tokenAnn.get(CharacterOffsetBeginAnnotation.class);
			int end = tokenAnn.get(CharacterOffsetEndAnnotation.class);
			TokenAnnotation token = new TokenAnnotation(jcas, begin, end);
			token.addToIndexes();
		}
	}






}
