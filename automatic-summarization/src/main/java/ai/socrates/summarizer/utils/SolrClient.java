package ai.socrates.summarizer.utils;

import java.io.IOException;
import java.util.HashMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrClient {

	private static final Logger logger = LoggerFactory.getLogger(SolrClient.class);
	static HashMap<String, Float> navCatThresholds;
	
//	static{
//		InputStream is3 = WNDClassifier.class.getClassLoader().getResourceAsStream("wordnet_domains/NavCatThresholds.txt");
//		try {
//			navCatThresholds = Misc.readTabSeparatedFloatMap(is3);
//		} catch (Exception e1) {
//			logger.error(e1.getMessage());
//		}
//	}


	public static void main( String[] args )
    {
		//getArticles("U.S. Defense Department");
		//logger.info(getSuggestion("suggest_persons", "Jer").toString());;
		
    }
		
	public SolrClient(){
	}
	
	public static SolrDocumentList getRelevantSections(String searchQuery, int clientId) {
		String urlString = PropertyHelper.getSolrEndPoint();
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");

		String outputFields = "id, source_document, source_document_url, section_name, content, page_number";
		String queryFields = "section_name, content";
		
		query.setQuery(searchQuery);
		//TBD: handle clientId
						
		query.set("qf", queryFields.replaceAll(",", " "));
		query.set("fl", outputFields);
		query.set("wt", "json");
		query.set("rows", "1");
		
		
		try {
			logger.info(query.toQueryString());
			QueryResponse response = solr.query(query);
			SolrDocumentList articles = response.getResults();
			if (articles!=null)
				logger.info("Returning " + articles.size() + " matches");
			else
				logger.warn("Result is null");
			return articles;
		} catch (SolrServerException e) {
			logger.error("Error getting document: " + e.getMessage());
			return null;
		} catch (IOException e) {
			logger.error("Error getting document: " + e.getMessage());
			return null;
		}
	}	
}
