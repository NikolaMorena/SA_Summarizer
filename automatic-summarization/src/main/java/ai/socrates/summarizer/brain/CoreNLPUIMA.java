package ai.socrates.summarizer.brain;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.CasPool;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class CoreNLPUIMA {
	private static AnalysisEngine ae = null;
	private static CasPool mCasPool;
	private static final Logger logger =LoggerFactory.getLogger(CoreNLPUIMA.class);
	private static boolean initialized=false;
	
	@PostConstruct
	public static void init() throws IOException, InvalidXMLException, ResourceInitializationException{
		logger.info("CoreNLP UIMA init started");
		try{
			URL url = CoreNLPUIMA.class.getClassLoader().getResource("descriptors/Main.xml");
			XMLInputSource in = new XMLInputSource(url);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
			ae = UIMAFramework.produceAnalysisEngine(specifier);
			mCasPool = new CasPool(5,ae);
			initialized=true;
			logger.info("UIMA initialized");
		}
		catch (Exception ex){
			logger.error("UIMA initialization failed: " + ex.getMessage());
		}
	}
	
	public JCas doNLP(String inputText){
		JCas jcas=null;
		try {
			jcas = mCasPool.getCas(0).getJCas();
		} catch (CASException e) {
			logger.error("Can't get Cas. The error is: " + e.getMessage());
			return null;
		}
		jcas.setDocumentText(inputText);
		try {
			ae.process(jcas);
			return jcas;
		} catch (AnalysisEngineProcessException e) {
			logger.error("AnalysisEngineProcessException: "+ e.getMessage());
			releaseCas(jcas);
			return null;
		}
	}
	
	public void releaseCas(JCas jcas){
		mCasPool.releaseCas(jcas.getCas());  
	}

	public static boolean isInitialized() {
		return initialized;
	}
	

}
