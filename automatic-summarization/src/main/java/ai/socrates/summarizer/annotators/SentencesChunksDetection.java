package ai.socrates.summarizer.annotators;


import java.io.InputStream;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.nlp.SentenceType;
import ai.socrates.summarizer.types.uima.QuestionAnnotation;
import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;
import ai.socrates.summarizer.utils.Misc;

import org.apache.uima.jcas.tcas.Annotation;


public class SentencesChunksDetection extends JCasAnnotator_ImplBase {
	private static final Logger logger =LoggerFactory.getLogger(SentencesChunksDetection.class);
	List<String> greetings;
	List<String> confirmations;
	List<String> negations;
	static String[] demonstratives={"here", "these", "those", "then"};
	
	@Override
	public void initialize(org.apache.uima.UimaContext aContext) throws org.apache.uima.resource.ResourceInitializationException {
		super.initialize(aContext);
	}


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		logger.debug("Process started");
//		Type sentenceType = aJCas.getTypeSystem().getType(sentenceTypeName);
//
						
		AnnotationIndex<Annotation> sentIndex = aJCas.getAnnotationIndex(SentenceAnnotation.type);
		FSIterator<Annotation> sentIterator = sentIndex.iterator();
		SentenceAnnotation currentSentence = null;

		SentencesChunkAnnotation lastChunk=null;
		int startOfLastIndependentSentence=0; 
		while (sentIterator.hasNext()){
			currentSentence = (SentenceAnnotation) sentIterator.next();
			String sentenceTextLowercased=currentSentence.getCoveredText().toLowerCase();
			boolean isDependent=sentenceTextLowercased.matches(".*\\b(however|by then)\\b.*");
			// bullet chars
			isDependent=isDependent || sentenceTextLowercased.matches("[\u2022,\u2023,\u25E6,\u2043,\u2219].*");
			isDependent=isDependent || sentenceTextLowercased.matches("(but|therefore|then|ironically|his|her|their|these|other|he|she|it|they|this|for example)\\b.*");
			//isDependent=isDependent || sentenceTextLowercased.startsWith("•");
			int firstCommaPos=sentenceTextLowercased.indexOf(",");
			if (firstCommaPos>-1 && !isDependent){
				for(String demonstrative:demonstratives){
					int demPos=sentenceTextLowercased.indexOf(demonstrative);
					if(demPos>-1){
						if(demPos<firstCommaPos){
							isDependent=true;
							break;
						}
					}
				}
			}
			
			if (!isDependent){
				startOfLastIndependentSentence=currentSentence.getBegin();
			}
			
			SentencesChunkAnnotation sc= new SentencesChunkAnnotation(aJCas);
			sc.setBegin(startOfLastIndependentSentence);
			sc.setEnd(currentSentence.getEnd());
			sc.setSentencesCount(1);
			sc.addToIndexes();
			
			if (isDependent){
				logger.debug("New multisentence chunk: " + sc.getCoveredText());
			}
			

//			if (!isDependent){
//				SentencesChunkAnnotation sc= new SentencesChunkAnnotation(aJCas);
//				sc.setBegin(currentSentence.getBegin());
//				sc.setEnd(currentSentence.getEnd());
//				sc.setSentencesCount(1);
//				sc.addToIndexes();
//				lastChunk=sc;
//			}
//			else{
//				if (lastChunk!=null){
//					lastChunk.setEnd(currentSentence.getEnd());
//					lastChunk.setSentencesCount(lastChunk.getSentencesCount()+1);
//					logger.info("New multisentence chunk: " + lastChunk.getCoveredText());
//				}
//				else{
//					logger.warn("Can't add dependent sentences to null chunk: " + currentSentence.getCoveredText());
//				}
//			}
		}
	}
		


}
