package com.nts.reservation.detail.dto;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
	private Long commentId;
	private Long productId;
	private int score;
	private String comment;
	private String reservationName;
	private String reservationEmail;
	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private List<CommentImage> commentImages;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
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

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", productId=" + productId + ", score=" + score + ", comment="
			+ comment + ", reservationName=" + reservationName + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ "]";
	}

}
