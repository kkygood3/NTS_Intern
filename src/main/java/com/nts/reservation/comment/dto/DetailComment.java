/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DetailComment {
	private Integer displayInfoId;
	private String comment;
	private String commentImage;
	private String reservationName;
	private String reservationDate;
	private String reservationEmail;
	private Integer score;
	private String productDescription;

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentImage() {
		return commentImage;
	}

	public void setCommentImage(String commentImage) {
		this.commentImage = commentImage;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "DetailComment [displayInfoId=" + displayInfoId + ", comment=" + comment + ", commentImage="
			+ commentImage + ", reservationName=" + reservationName + ", reservationDate=" + reservationDate
			+ ", score=" + score + ", productDescription=" + productDescription + "]";
	}
}
