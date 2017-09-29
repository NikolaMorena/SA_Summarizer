package ai.socrates.summarizer.utils;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.socrates.summarizer.types.uima.SentenceAnnotation;
import ai.socrates.summarizer.types.uima.SentencesChunkAnnotation;
import ai.socrates.summarizer.types.uima.TokenAnnotation;

public class Misc {
	private static final Logger logger =LoggerFactory.getLogger(Misc.class);

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
				return -(o1.getValue()).compareTo( o2.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}

	public static List<SentencesChunkAnnotation> getSummary(Map<SentencesChunkAnnotation, Double> relevanceSummarizerScores, JCas jcas, double percentageOfOriginalDocument) {
		List<SentencesChunkAnnotation> ret = new ArrayList<>();

		if(relevanceSummarizerScores == null || relevanceSummarizerScores.size() == 0 || jcas == null) {
			return null;
		}

		if(percentageOfOriginalDocument < 0 || percentageOfOriginalDocument > 100) {
			percentageOfOriginalDocument=20d;
			logger.warn("Length percentage of summary wrongly defined - set to 20%.");
		}

		double totalLengthDoc = jcas.getDocumentText().length();
		double totalLengthSummary = 0d;
		Map<Integer, SentencesChunkAnnotation> selectedStartPositions= new LinkedHashMap<>(); // only one of the chunks that start at the same position may be selected 
		for(Entry<SentencesChunkAnnotation, Double> sEntry : relevanceSummarizerScores.entrySet()) {
			if (sEntry.getValue()==0f)
				break;
			SentencesChunkAnnotation currentChunk= sEntry.getKey();

			if (selectedStartPositions.containsKey(currentChunk.getBegin())){
				SentencesChunkAnnotation addedChunk=selectedStartPositions.get(currentChunk.getBegin());
				if ((addedChunk.getEnd()-addedChunk.getBegin())>(currentChunk.getEnd()-currentChunk.getBegin())){
					logger.info("Skipped as there's already a selected chunk starting at the same position: " + currentChunk.getCoveredText());
				}
				else{
					selectedStartPositions.put(currentChunk.getBegin(), currentChunk);
					totalLengthSummary += currentChunk.getCoveredText().length()-addedChunk.getCoveredText().length();
					logger.info("Replacing previous shorter chunk starting at the same position: " + currentChunk.getCoveredText());
				}
			}
			else{
				selectedStartPositions.put(currentChunk.getBegin(), currentChunk);
				totalLengthSummary += currentChunk.getCoveredText().length();
				logger.debug("Adding chunk to map: " + currentChunk.getCoveredText());
			}
			if( ((totalLengthSummary / totalLengthDoc) * 100d) > percentageOfOriginalDocument) {
				break;
			}
		}
		return getChunkListFromMap(selectedStartPositions);
	}

	public static List<SentencesChunkAnnotation> getSummary(Map<SentencesChunkAnnotation, Double> orderedSentences_SemScore, JCas jcas, int sentCount) {
		Map<Integer, SentencesChunkAnnotation> selectedStartPositions= new LinkedHashMap<>(); // only one of the chunks that start at the same position may be selected 
		int cnt=0;
		for(Entry<SentencesChunkAnnotation, Double> sEntry : orderedSentences_SemScore.entrySet()) {
			if (sEntry.getValue()==0f)
				break;
			SentencesChunkAnnotation currentChunk= sEntry.getKey();
			int sentencesLeft=sentCount-cnt;
			if (currentChunk.getSentencesCount()<=sentencesLeft){
				if (selectedStartPositions.containsKey(currentChunk.getBegin())){
					SentencesChunkAnnotation addedChunk=selectedStartPositions.get(currentChunk.getBegin());
					if ((addedChunk.getEnd()-addedChunk.getBegin())>(currentChunk.getEnd()-currentChunk.getBegin())){
						logger.info("Skipped as there's already a selected chunk starting at the same position: " + currentChunk.getCoveredText());
					}
					else{
						selectedStartPositions.put(currentChunk.getBegin(), currentChunk);
						cnt+=currentChunk.getSentencesCount()-addedChunk.getSentencesCount();
						logger.info("Replacing previous shorter chunk starting at the same position: " + currentChunk.getCoveredText());
					}
				}
				else
					selectedStartPositions.put(currentChunk.getBegin(), currentChunk);
				cnt+=currentChunk.getSentencesCount();
				logger.debug("Adding chunk to map: " + currentChunk.getCoveredText());
			}
			if (cnt>=sentCount)
				break;
		}

		return getChunkListFromMap(selectedStartPositions);
	}

	private static List<SentencesChunkAnnotation> getChunkListFromMap(Map<Integer, SentencesChunkAnnotation> selectedStartPositions){
		List<SentencesChunkAnnotation> ret = new LinkedList<>();
		for (Integer i:selectedStartPositions.keySet()){
			SentencesChunkAnnotation currentChunk= selectedStartPositions.get(i);
			logger.debug("Adding chunk to list: " + currentChunk.getCoveredText());
			ret.add(currentChunk);
			List<SentenceAnnotation> sentences=JCasUtil.selectCovered(SentenceAnnotation.class, currentChunk);
			for (SentenceAnnotation s:sentences)
				s.setSelectedForSummary(true);
		}

		return ret;
	}


	public static Map<String, String> readMapFromStream(InputStream is, String separator, boolean lowerCaseKeys) throws Exception{
		return readMapFromStream(is, separator, lowerCaseKeys, "Unicode");
	}

	public static Map<String, String> readMapFromStream(InputStream is, String separator, boolean lowerCaseKeys, String encoding) throws Exception{
		Map<String, String> hm = new LinkedHashMap<>();
		Scanner sc = new Scanner(is, encoding);
		//Scanner sc = new Scanner(is);
		int lineNumber=1;
		while (sc.hasNext() )
		{
			String line = sc.nextLine(); //.trim();
			if (!line.startsWith("#")){
				try {
					String key = line.substring(0, line.indexOf(separator)).trim();
					if (lowerCaseKeys)
						key=key.toLowerCase();
					String value="";
					if (line.indexOf(separator)<line.length()-1)
						value = line.substring(line.indexOf(separator) + 1).trim();
					else
						logger.warn("Empty value for the key " + key);
					hm.put(key, value);
				}
				catch (StringIndexOutOfBoundsException ex){
					logger.warn("Bad line(" + lineNumber + "): "+ line);
				}
			}
			lineNumber++;
		}
		sc.close();
		return hm;	
	}

	public static String readJSONFromFile(InputStream is) throws Exception{
		StringBuilder sb= new StringBuilder();
		Scanner sc = new Scanner(is);
		while (sc.hasNext() )
		{
			String line = sc.nextLine().trim();
			sb.append(line);
		}
		sc.close();
		return sb.toString();	
	}

	public static boolean isMathExpression(String strLine)
	{
		strLine = strLine.replaceAll("\\+|\\-|\\*|\\/|\\%","MatExp");
		strLine = strLine.replaceAll("ln|log|sin|cos|tg|ctg|tan|cot","MatExp");
		strLine = strLine.replaceAll("\\(|\\)| ","");
		String np="([0-9]*\\.?[0-9]+|pi|e)";
		return (strLine.matches("(MatExp|" +np+")+=?\\??"));  
	}

	public static List<String> readList(InputStream is, Boolean toLowerCase) {
		List<String> list = new ArrayList<String>();
		Scanner sc = new Scanner(is, "UTF-8");
		while (sc.hasNext()) {
			String line = sc.nextLine().trim();
			if (!line.startsWith("#") && !line.isEmpty())
				if (toLowerCase)
					list.add(line.toLowerCase());
				else
					list.add(line);
		}
		sc.close();
		return list;
	}

	public static HashMap<String, List<String>> readTabSeparatedMap(InputStream is) throws Exception {
		// file line pattern: key1{tab}value1 value2... valueN

		HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
		Scanner sc = new Scanner(is);
		while (sc.hasNext()) {
			String line = sc.nextLine().trim();

			// if (line.startsWith("#") || line.isEmpty())
			// continue;

			String[] lineElements = line.split("\\t");
			if (lineElements.length != 2) {
				sc.close();
				throw new Exception("Bad input file");
			}
			String key = lineElements[0];
			String[] values = lineElements[1].split(" ");
			// list (map elements)
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i].trim());
			}
			hm.put(key.trim().toLowerCase(), list);
		}
		sc.close();
		return hm;
	}

	public static HashMap<String, String> readTabSeparatedStringMap(InputStream is) throws Exception {
		// file line pattern: key1{tab}value1

		HashMap<String, String> hm = new HashMap<String, String>();
		Scanner sc = new Scanner(is);
		while (sc.hasNext()) {
			String line = sc.nextLine().trim();

			if (line.startsWith("#") || line.isEmpty())
				continue;

			String[] lineElements = line.split("\\t");
			if (lineElements.length != 2 || line.contains(" ")) {
				sc.close();
				throw new Exception("Bad input file");
			}
			String key = lineElements[0];
			String value = lineElements[1];
			hm.put(key.trim().toLowerCase(), value.trim().toLowerCase());
		}
		sc.close();
		return hm;
	}

	public static void sortByPosition(List<SentencesChunkAnnotation> summarySentences) {
		Collections.sort(summarySentences, new Comparator<SentencesChunkAnnotation>() {
			@Override
			public int compare(SentencesChunkAnnotation o1, SentencesChunkAnnotation o2) {
				return o1.getBegin()>o2.getBegin()?1:-1;
			}
		} );
		
	}


}
