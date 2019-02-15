/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.detail;

/**
 * detail 페이지 로드 시 /api/products/{displayInfoId} 요청에  detail 페이지의 Comment 정보를 담는 DTO
 */
public class DetailComment {
	private Integer displayInfoId;
	private String comment;
	private String commentImage;
	private String reservationName;
	private String reservationDate;
	private Integer score;

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
