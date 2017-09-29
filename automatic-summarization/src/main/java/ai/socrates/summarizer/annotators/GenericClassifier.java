package ai.socrates.summarizer.annotators;


import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.nlp.QuestionFactuality;
import ai.socrates.summarizer.types.nlp.QuestionType;
import ai.socrates.summarizer.types.uima.QuestionAnnotation;
import ai.socrates.summarizer.utils.Misc;

import org.apache.uima.jcas.tcas.Annotation;




public class GenericClassifier extends JCasAnnotator_ImplBase {
	private static final Logger logger =LoggerFactory.getLogger(GenericClassifier.class);
		
	
	Map<Pattern, String> factualityMap=new LinkedHashMap<>();
	Map<Pattern, String> qTypeMap=new LinkedHashMap<>();
	Map<Pattern, String> aClassMap=new LinkedHashMap<>();
	
	@Override
	public void initialize(org.apache.uima.UimaContext aContext) throws org.apache.uima.resource.ResourceInitializationException {
		super.initialize(aContext);
		logger.info("Initialization started");

		logger.info("Loading factuality regular expressions");
		InputStream isFactuality = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/FactualityRegex.txt");
		try {
			Map<String, String> map = Misc.readMapFromStream(isFactuality, "\t", false);
			for (String pattern:map.keySet()){
				Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
				factualityMap.put(p, map.get(pattern));
			}
			logger.info(factualityMap.size() + " factuality regular expressions loaded");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Loading question types regular expressions");
		InputStream isQuestionType = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/QuestionTypesRegex.txt");
		try {
			Map<String, String> map = Misc.readMapFromStream(isQuestionType, "\t", false);
			for (String pattern:map.keySet()){
				Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
				qTypeMap.put(p, map.get(pattern));
			}
			logger.info(qTypeMap.size() + " question types regular expressions loaded");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Loading answer class regular expressions");
		InputStream isAnswerClass = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/AnswerClassesRegex.txt");
		try {
			Map<String, String> map = Misc.readMapFromStream(isAnswerClass, "\t", false);
			for (String pattern:map.keySet()){
				Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
				aClassMap.put(p, map.get(pattern));
			}
			logger.info(aClassMap.size() + " answer class regular expressions loaded");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Initializetion completed");
	}


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		logger.debug("Process started");
//		Type sentenceType = aJCas.getTypeSystem().getType(sentenceTypeName);
//
						
		AnnotationIndex<Annotation> qIndex = aJCas.getAnnotationIndex(QuestionAnnotation.type);
		FSIterator<Annotation> qIterator = qIndex.iterator();
		QuestionAnnotation lastQuestion = null;
		
		while (qIterator.hasNext()){
			lastQuestion = (QuestionAnnotation) qIterator.next();
			lastQuestion.setFactuality(detectFactuality(lastQuestion.getCoveredText()));
			lastQuestion.setQuestionType(detectQuestionType(lastQuestion.getCoveredText()));
			logger.debug(lastQuestion.getCoveredText());
			logger.debug("Factuality: " + lastQuestion.getFactuality());
			logger.debug("Question Type: " + lastQuestion.getQuestionType());	
			logger.debug("Answer class: " + lastQuestion.getAnswerClass());	
		}
		
		//no sentences
		if(lastQuestion == null){
			logger.info("No questions");
			return;
		}
	}
	
	
	private String detectFactuality(String question) {
		String factuality=QuestionFactuality.Factual.name();
		if (factualityMap!=null){
			for (Pattern p:factualityMap.keySet()){
				Matcher m = p.matcher(question);
				if (m.find()){
					factuality=factualityMap.get(p);
					logger.debug("Pattern " + p + " matched, factuality is " + factuality);
					break;
				}
				else{
					logger.trace("Pattern " + p + " not matched");
				}
			}
		}
		else
			logger.error("Factuality map is null");
		return factuality;
	}
	
	private String detectQuestionType(String question) {
		String questionType=QuestionType.Misc.name();
		if (qTypeMap!=null){
			for (Pattern p:qTypeMap.keySet()){
				Matcher m = p.matcher(question);
				if (m.find()){
					questionType=qTypeMap.get(p);
					logger.debug("Pattern " + p + " matched, question type is " + questionType);
					break;
				}
				else{
					logger.trace("Pattern " + p + " not matched");
				}
			}
		}
		else
			logger.error("Question Type map is null");
		return questionType;
	}
	
		
	

}
