/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.util.Date;
import java.util.List;

/**
 * @author 육성렬
 */
public class CommentDto {
	private String comment;
	private Long commentId;
	private List<CommentImageDto> commentImages;
	private Date createDate;
	private Date modifyDate;
	private Long productId;
	private Date reservationDate;
	private String reservationEmail;
	private Long reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private Float score;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public List<CommentImageDto> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImageDto> commentImages) {
		this.commentImages = commentImages;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "CommentDto [comment=" + comment + ", commentId=" + commentId + ", commentImages=" + commentImages
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", score=" + score + "]";
	}

}
