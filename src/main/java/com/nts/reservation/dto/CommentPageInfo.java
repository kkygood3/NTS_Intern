package com.nts.reservation.dto;

public class CommentPageInfo {
	private long productId;
	private String description;
	private double averageScore;
	private int commentCount;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAverageScore() {
		return String.format("%.1f", averageScore);

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
		return "ReviewPageInfo [productId=" + productId + ", description=" + description + ", averageScore="
				+ averageScore + ", commentCount=" + commentCount + "]";
	}
}
