package ai.socrates.summarizer.types.nlp;

import java.util.ArrayList;
import java.util.List;

public class MultiDocumentSummary {
	private String summaryText;
	private List<SummarizedDocument> summarizedDocuments= new ArrayList<>();
	
	public String getSummaryText() {
		return summaryText;
	}
	public void setSummaryText(String summaryText) {
		this.summaryText = summaryText;
	}
	public List<SummarizedDocument> getSummarizedDocuments() {
		return summarizedDocuments;
	}
	public void setSummarizedDocuments(List<SummarizedDocument> summarizedDocuments) {
		this.summarizedDocuments = summarizedDocuments;
	}


}
