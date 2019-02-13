/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dto;

import java.util.List;

public class CommentResponse {
	private String comment;
	private Integer commentId;
	private List<CommentImage> commentImage;
	private String createDate;
	private String modifyDate;
	private Integer productId;
	private Integer reservationInfoId;
	private Integer score;

	public String getComment() {
		return comment;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public List<CommentImage> getCommentImage() {
		return commentImage;
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

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public Integer getScore() {
		return score;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public void setCommentImage(List<CommentImage> commentImage) {
		this.commentImage = commentImage;
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

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "CommentResponse [comment=" + comment + ", commentId=" + commentId + ", commentImage=" + commentImage
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationInfoId=" + reservationInfoId + ", score=" + score + "]";
	}
}
