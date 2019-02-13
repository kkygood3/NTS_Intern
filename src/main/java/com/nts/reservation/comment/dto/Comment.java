/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dto;

import java.util.List;

public class Comment {
	private String comment;
	private Integer commentId;
	private List<CommentImage> commentImages;
	private String createDate;
	private String modifyDate;
	private Integer productId;
	private String reservationDate;
	private String reservationEmail;
	private Integer reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private double score;

	public String getComment() {
		return comment;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public double getScore() {
		return score;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", commentId=" + commentId + ", commentImages=" + commentImages
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", score=" + score + "]";
	}
}
