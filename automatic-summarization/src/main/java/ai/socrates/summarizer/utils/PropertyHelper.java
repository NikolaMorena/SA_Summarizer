package ai.socrates.summarizer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertyHelper {
	static Properties props = new Properties();
	private static final Logger logger =LoggerFactory.getLogger(PropertyHelper.class);
	
	static{
		try {
            InputStream inputStream =
            		PropertyHelper.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(inputStream);
            Map<String, String> env = System.getenv();
            for(String key:env.keySet()){
            	if (props.containsKey(key)){
            		logger.info("Overriding " +  key + " with " + env.get(key));
            		System.out.println("Overriding " +  key + " with " + env.get(key));
            		props.setProperty(key, env.get(key));
            	}	
            }
            if (new File("application.properties").exists()){
            	logger.info("External properties found");
        		System.out.println("External properties found");
            	FileInputStream externalPropertiesStream = new FileInputStream("application.properties");
            	Properties externalProperties= new Properties();
            	externalProperties.load(externalPropertiesStream);
            	props.putAll(externalProperties);
            }
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public static String getArticlesPath(){
		return props.getProperty("articles.path");
	}
	
	public static String getSolrEndPoint() {
		return props.getProperty("solr.endPoint");
	}
}
