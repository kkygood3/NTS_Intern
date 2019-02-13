package com.nts.reservation.dto.detail;

import java.time.LocalDateTime;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class Comment {
	private Long commentId;
	private Long productId;
	private Long reservationInfoId;
	private Double score;
	private String comment;
	private String reservationTelephone;
	private String reservationName;
	private String reservationEmail;
	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private List<CommentImage> commentImages = new ArrayList<CommentImage>();

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", productId=" + productId + ", reservationInfoId="
			+ reservationInfoId + ", score=" + score + ", comment=" + comment + ", reservationTelephone="
			+ reservationTelephone + ", reservationName=" + reservationName + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ ", commentImages=" + commentImages + "]";
	}

}
