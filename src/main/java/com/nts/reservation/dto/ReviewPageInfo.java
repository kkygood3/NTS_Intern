package com.nts.reservation.dto;

public class ReviewPageInfo {
	private long displayInfoId;
	private String description;
	private double averageScore;
	private int commentCount;

	public long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "ReviewPageInfo [displayInfoId=" + displayInfoId + ", description=" + description + ", averageScore="
			+ averageScore + ", commentCount=" + commentCount + "]";
	}
}
