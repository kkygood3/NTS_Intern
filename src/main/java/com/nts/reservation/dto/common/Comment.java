/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.common;

/**
 * detail, review 페이지에서 사용하는 Comment 정보 DTO
 */
public class Comment {
	private Integer displayInfoId;
	private String comment;
	private String commentImage;
	private String reservationName;
	private String reservationDate;
	private Integer score;
	private Integer reservationUserCommentImageId;

	public Integer getReservationUserCommentImageId() {
		return reservationUserCommentImageId;
	}

	public void setReservationUserCommentImageId(Integer reservationUserCommentImageId) {
		this.reservationUserCommentImageId = reservationUserCommentImageId;
	}

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
