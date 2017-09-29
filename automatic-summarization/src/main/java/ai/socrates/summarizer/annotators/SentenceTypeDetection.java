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
import ai.socrates.summarizer.utils.Misc;

import org.apache.uima.jcas.tcas.Annotation;


public class SentenceTypeDetection extends JCasAnnotator_ImplBase {
	private static final Logger logger =LoggerFactory.getLogger(SentenceTypeDetection.class);
	List<String> greetings;
	List<String> confirmations;
	List<String> negations;
	
	@Override
	public void initialize(org.apache.uima.UimaContext aContext) throws org.apache.uima.resource.ResourceInitializationException {
		super.initialize(aContext);
		
		try {
			InputStream iscw = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/Greetings.txt");
			try {
				greetings = Misc.readList(iscw, true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			iscw = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/Yes.txt");
			try {
				confirmations = Misc.readList(iscw, true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			iscw = getClass().getClassLoader().getResourceAsStream("dictionaries/sentencies/No.txt");
			try {
				negations = Misc.readList(iscw, true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			logger.info("Annotator initialized");
		} 
		catch (Exception e) {
			logger.error( "Initialization failed. " + e.getMessage());
		}
	}


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		logger.debug("Process started");
//		Type sentenceType = aJCas.getTypeSystem().getType(sentenceTypeName);
//
						
		AnnotationIndex<Annotation> sentIndex = aJCas.getAnnotationIndex(SentenceAnnotation.type);
		FSIterator<Annotation> sentIterator = sentIndex.iterator();
		SentenceAnnotation lastSentence = null;
		
		while (sentIterator.hasNext()){
			lastSentence = (SentenceAnnotation) sentIterator.next();
			String sentenceText=lastSentence.getCoveredText();
			
			if (Misc.isMathExpression(sentenceText)){
				lastSentence.setSentenceType(SentenceType.Question.name());
			}
			else if (sentenceText.endsWith("?")){
				lastSentence.setSentenceType(SentenceType.Question.name());
			}
			else if (isGreeting(sentenceText)){
				lastSentence.setSentenceType(SentenceType.Greeting.name());
			}
			else if (isConfirmation(sentenceText)){
				lastSentence.setSentenceType(SentenceType.Confirmation.name());
			}
			else if (isNegation(sentenceText)){
				lastSentence.setSentenceType(SentenceType.Negation.name());
			}
			else{
				String pType="";
				try {
					pType=lastSentence.getTopTreebankTag();
					if (pType==null) //parser is probably off
						pType="SQ";
				}
				catch (Exception ex){
					logger.warn("Problem getting pType for " + lastSentence.getCoveredText());
				}
				// http://web.mit.edu/6.863/www/PennTreebankTags.html
				if (pType.matches("SQ|SBARQ|SINV|SBAR")){
					lastSentence.setSentenceType(SentenceType.Question.name());
				}
				else if (lastSentence.getCoveredText().endsWith("!")){
					if (pType.matches("VB|VP"))
						lastSentence.setSentenceType(SentenceType.Command.name());
					else
						lastSentence.setSentenceType(SentenceType.Interjection.name());
				}
				//interjection Hi, Hello ... http://partofspeech.org/interjection/
				else if (pType.matches("INTJ|UH")){
					lastSentence.setSentenceType(SentenceType.Interjection.name());
				}
				else if (pType.matches("VB|VP")){
					lastSentence.setSentenceType(SentenceType.Command.name());
				}
				else if (pType.matches("NP")){
					lastSentence.setSentenceType(SentenceType.Declaration.name());
				}
			}
			if (lastSentence.getSentenceType()==null){
				String def=SentenceType.Declaration.name();
				logger.warn("Sentence Type isn't detected. Using default " + def);
				lastSentence.setSentenceType(def);
			}
			else{
				logger.debug("Sentence Type: " + lastSentence.getSentenceType());
			}
			
			if (lastSentence.getSentenceType().equals(SentenceType.Declaration.name()) ||
					lastSentence.getSentenceType().equals(SentenceType.Command.name())){
				if (sentenceText.toLowerCase().matches(".*(need|help).*")){
					lastSentence.setSentenceType(SentenceType.NeedHelp.name());
					logger.debug("Sentence Type modified to " + lastSentence.getSentenceType());
				}
			}
			
			if (lastSentence.getSentenceType().equals(SentenceType.Question.name())){
				QuestionAnnotation qa= new QuestionAnnotation(aJCas);
				qa.setBegin(lastSentence.getBegin());
				qa.setEnd(lastSentence.getEnd());
				qa.addToIndexes();
				logger.debug("Question annotation added: " + qa.getCoveredText());
			}
				
		}
		
	

		//no sentences
		if(lastSentence == null){
			logger.warn( "No sentences");
			return;
		}

//		//get last token in sentence
//		FSArray tokens = lastSentence.getTokens();
//		int tokenArraySize = 0;
//		if (tokens != null) {
//			tokenArraySize = tokens.size();
//		}
//		Token lastToken = (Token) tokens.get(tokenArraySize - 1);
//
//		// get parse tree root type
//		// Type parseTreeRootType =
//		// aJCas.getTypeSystem().getType(treeRootTypeName);
//
//		// get TOP for ParseTree from CAS
//		// FSIterator<FeatureStructure> rootIterator =
//		// aJCas.getIndexRepository().getIndex("StanfordParseTreeRootIndex",
//		// parseTreeRootType).iterator();
//		// while (rootIterator.hasNext()) {
//
//		//ParseTree annotations now is available from Sentence 
//		ParseTreeRootNodeSt tree = (ParseTreeRootNodeSt) lastSentence.getParseTreeAnnotations();
//		if (tree != null) {
//			
//			ParseTreeSt topNode = tree.getRootNode();
//			if (topNode.getBegin() == lastSentence.getBegin() && topNode.getEnd() == lastSentence.getEnd()) 
//			{
//				HashMap<Integer, ArrayList<String>> treeTagsOrderedByLevels = bfsTreeVisit(topNode);
//
//				// loop through tags map and compose results
//				for (Map.Entry<Integer, ArrayList<String>> entry : treeTagsOrderedByLevels.entrySet()) 
//				{
//					int level = entry.getKey();
//					ArrayList<String> levelTags = entry.getValue();
//					
//					if (testing) {
//						// only for testing print tags by levels
//						System.out.print(Integer.toString(level) + " : ");
//						for (String valueTags : entry.getValue()) {
//							System.out.print(valueTags + " ");
//						}
//						System.out.println();
//					}
//					
//					SentencePurpose lsp = null;
//					InputTextStructure its = null;
//					
//					if (level == 1) {
//
//						if (levelTags.size() > 1) {
//							lastSentence.setIsRegular(false);
//							logger.log(Level.WARNING,"Input Text Parse Tree is wrong");
//							break;
//						} else {
//							
//							String nodeTag = levelTags.get(0);
//							if (nodeTag.equals("S")) {
//								
//								lastSentence.setIsRegular(true);
//								lsp = SentencePurpose.Declaration;
//								
//								if (lastToken.getCoveredText().equals("!")) {
//									lsp = SentencePurpose.Exclamation;
//								}
//							}
//							else if (nodeTag.matches("SQ|SBARQ|SINV")) {
//								
//								lastSentence.setIsRegular(true);
//								lsp = SentencePurpose.Question;
//								
//							}
//							else if (nodeTag.matches("FRAG|NP|VP|INTJ")) {
//								lastSentence.setIsRegular(false);
//								if (lastToken.getCoveredText().equals("!")) {
//									lsp = SentencePurpose.Exclamation;
//								}
//								else if (lastToken.getCoveredText().equals("?")){
//									lsp = SentencePurpose.Question;
//								}
//								else
//									lsp=SentencePurpose.Declaration;
//								
//							} else {
//								lastSentence.setIsRegular(false);
//
//								if (lastToken.getCoveredText().equals(".")) {
//									lsp = SentencePurpose.Declaration;
//								}
//
//							}
//						}
//					}
//
//					if (level == 2) {
//
//						String[] compoundTagsInSecondLevel = { "S", "CC", "IN", "SINV", ",", ".", ":" };
//						String[] questionCompoundTagsInSecondLevel = { "SQ", "CC", ",", "." };
//						String[] commandTagsInSecondLevel = { "VP", "." };
//
//						ArrayList<Integer> compoundMatches = new ArrayList<Integer>();
//						ArrayList<Integer> questionCompoundMatces = new ArrayList<Integer>();
//						ArrayList<Integer> commandMatches = new ArrayList<Integer>();
//
//						boolean sbarTag = false;
//						
//						Iterator<String> tagsIterator = levelTags.iterator();
//						while (tagsIterator.hasNext()) {
//							String tag = (String) tagsIterator.next();
//							if (tag.equals("SBAR"))
//								sbarTag = true;
//							for (int i = 0; i < compoundTagsInSecondLevel.length; i++) {
//								if (tag.equals(compoundTagsInSecondLevel[i]))
//									compoundMatches.add(1);
//							}
//							for (int i = 0; i < commandTagsInSecondLevel.length; i++) {
//								if (tag.equals(commandTagsInSecondLevel[i]))
//									commandMatches.add(1);
//							}
//							for (int i = 0; i < questionCompoundTagsInSecondLevel.length; i++) {
//								if (tag.equals(questionCompoundTagsInSecondLevel[i]))
//									questionCompoundMatces.add(1);
//							}
//						}
//
//						if (sbarTag) {
//							its = InputTextStructure.ComplexSentence;
//						} else if (compoundMatches.size() == levelTags.size() || questionCompoundMatces.size() == levelTags.size()) {
//							its = InputTextStructure.CompoundSentence;
//						} else {
//							its = InputTextStructure.SimpleSentence;
//						}
//
//						if (commandMatches.size() == levelTags.size()) {
//
//							//lsp = SentencePurpose.Command;
//							if (lastToken.getCoveredText().equals("!")) {
//								lsp = SentencePurpose.Exclamation;
//							}
//						}
//
//					}
//					if (level > 2) {
//						
//						boolean sBarTag = false;
//						Iterator<String> tagsIterator = levelTags.iterator();
//						while (tagsIterator.hasNext()) {
//							String tag = (String) tagsIterator.next();
//							if (tag.equals("SBAR"))
//								sBarTag = true;
//						}
//
//						if (sBarTag) {
//							if (lastSentence.getStructure().equals("SimpleSentence"))
//								its = InputTextStructure.ComplexSentence;
//							if (lastSentence.getStructure().equals("CompoundSentence"))
//								its = InputTextStructure.ComplexCompoundSentence;
//						}
//
//					}
//
//					if (lastToken.getCoveredText().equals("?")) {
//						lsp = SentencePurpose.Question;
//					}
//
//					if (lsp != null) {
//						lastSentence.setPurpose(lsp.toString());
//					}
//					if (its != null)
//						lastSentence.setStructure(its.toString());
//				}//end with level tree visiting
//
//				//first version of commands search: find VB-VBG tags in deep...
///*				String[][] deepCommandTags = {{ "S", "VP", "VBG", "TP" }, { "S", "VP", "VBP", "TP" }, { "S", "VP", "VB", "TP" }};
//				boolean deepTagsExists = false;
//				for (int i = 0; i < 3; i++) { 
//					deepTagsExists = dfsSearchTreeForTagsFromList(topNode, deepCommandTags[i], 0);
//					if (deepTagsExists)
//						break;
//				}
//
//				if (deepTagsExists && !lastSentence.getPurpose().equals("Exclamation") && (lastSentence.getStructure().equals("SimpleSentence"))) {
//					SentencePurpose lsp = SentencePurpose.Command;
//					lastSentence.setPurpose(lsp.toString());
//				}
//*/
//				//second version of commands search: find VB pos tags in begin of the sentence 
//				StringArray sentenceTokenTags = tree.getTokenTags();
//				if(sentenceTokenTags != null){
//					String[] tokenTag = sentenceTokenTags.get(0).split("/");
//					if (tokenTag.length>0){
//						String tag = tokenTag[1];
//						boolean purposeOk=true;
//						if (lastSentence.getPurpose()!=null)
//							purposeOk=lastSentence.getPurpose().equals("Declaration");
//						if((tag.equals("VB") || tag.equals("VBG")) && purposeOk){
//							SentencePurpose lsp = SentencePurpose.Command;
//							lastSentence.setPurpose(lsp.toString());
//						}
//					}
//				}
//			}
//		}
//		
//		if (testing) {
//			// only for test purpose print in file all sentences with their structure and purpose components
//			String clasificationString = lastSentence.getCoveredText() + "\t" + lastSentence.getStructure() + "\t"
//					+ lastSentence.getPurpose() + " \t " + Boolean.toString(lastSentence.getIsRegular()) + "\n";
//			try {
//				TextFileManipulation.appendFile("SentenceTextClasification.txt", clasificationString);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		//}
//		logger.log(Level.INFO, "InputTextClassification process finished");

	}
	
	private boolean isGreeting(String text){
		text=text.replaceAll("'s", "is").replaceAll("\\?", "").replaceAll("!", "").replaceAll("\\.","");
		return greetings.contains(text.toLowerCase());
	}
	
	private boolean isConfirmation(String text){
		text=text.replaceAll("'s", "is").replaceAll("\\?", "").replaceAll("!", "").replaceAll("\\.","");
		return confirmations.contains(text.toLowerCase());
	}
	
	private boolean isNegation(String text){
		text=text.replaceAll("'s", "is").replaceAll("\\?", "").replaceAll("!", "").replaceAll("\\.","");
		return negations.contains(text.toLowerCase());
	}

}
