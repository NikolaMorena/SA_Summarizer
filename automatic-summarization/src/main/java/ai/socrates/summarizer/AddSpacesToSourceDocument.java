package ai.socrates.summarizer;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ai.socrates.summarizer.utils.PropertyHelper;

/**
 * Hello world!
 *
 */
public class AddSpacesToSourceDocument 
{
	private static final Logger logger =LoggerFactory.getLogger(AddSpacesToSourceDocument.class);
	public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException, TransformerException
    {
    		
		Pattern p = Pattern.compile("\\b([A-Z]|[a-z])+\\.[A-Z][a-z]+\\b");
	    	String articlePath="C:\\Users\\nikola\\Dropbox\\Socrates\\xml\\WF-Handbook.xml";
			File fXmlFile = new File(articlePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	
			if (fXmlFile.exists()){
				Document doc = dBuilder.parse(fXmlFile);
				Element articleElement= (Element) doc.getDocumentElement(); //.getElementsByTagName("article").item(0);
				NodeList sectionNodes=articleElement.getElementsByTagName("section");
				boolean update=false;
				for(int i=0; i<sectionNodes.getLength(); i++){
					Element sectionElement=(Element) sectionNodes.item(i); 
					NodeList contentNodes= sectionElement.getElementsByTagName("content");
					for(int j=0; j<contentNodes.getLength(); j++){
						Element contentElement=(Element) contentNodes.item(j);
						String contentText=contentElement.getTextContent();
						if (contentText.length()>1){
							logger.info("Checking " + contentText);
							//String intro=articleText.substring(0,200);
							Matcher matcher = p.matcher(contentText);
							String updatedText=contentText;
							while (matcher.find()) {
								update=true;
								String toBeReplaced=matcher.group();
								String replacement = toBeReplaced.replace(".", ". ");
								//logger.info(articleId +": "+toBeReplaced+ "->" +replacement);
								updatedText=updatedText.replace(toBeReplaced, replacement);
							}
							contentElement.setTextContent(updatedText);
						}
						else{
							logger.trace(articlePath + " text length is " + contentText.length());
						}
					}
				}
//				if (update){
//					DOMSource source = new DOMSource(doc);
//					StreamResult result = new StreamResult(new File(articlePath));
//					transformer.transform(source, result);
//					logger.info(articlePath +" saved");
//				}
			}
			else{
				logger.trace(articlePath + " is missing");
			}
			
    }
}
