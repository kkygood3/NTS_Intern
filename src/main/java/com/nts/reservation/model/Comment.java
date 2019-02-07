/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

public class Comment {

	private String productDescription;
	private String reservationDate;
	private String reservationEmail;
	private String comment;
	private int score;
	private String commentImageUrl;

	@Override
	public String toString() {
		return "Comment [productDescription=" + productDescription + ", reservationDate=" + reservationDate
			+ ", reservationEmail=" + reservationEmail + ", comment=" + comment + ", score=" + score
			+ ", commentImageUrl=" + commentImageUrl + "]";
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCommentImageUrl() {
		return commentImageUrl;
	}

	public void setCommentImageUrl(String commentImageUrl) {
		this.commentImageUrl = commentImageUrl;
	}

}
