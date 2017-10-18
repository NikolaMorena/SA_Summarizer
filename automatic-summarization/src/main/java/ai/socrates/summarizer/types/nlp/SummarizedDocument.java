package ai.socrates.summarizer.types.nlp;

import java.util.ArrayList;
import java.util.List;

public class SummarizedDocument {
	private String id;
	private String title;
	private String text;
	private String manualSummary;
	private String summary;
	private List<String> keywords= new ArrayList<>();
	private float bestSentenceScore;
	private float cummulativeScore;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManualSummary() {
		return manualSummary;
	}
	public void setManualSummary(String manualSummary) {
		this.manualSummary = manualSummary;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public float getBestSentenceScore() {
		return bestSentenceScore;
	}
	public void setBestSentenceScore(float bestSentenceScore) {
		this.bestSentenceScore = bestSentenceScore;
	}
	public float getCummulativeScore() {
		return cummulativeScore;
	}
	public void setCummulativeScore(float cummulativeScore) {
		this.cummulativeScore = cummulativeScore;
	}
}
