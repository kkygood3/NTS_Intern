package com.nts.reservation.dto;

import java.time.LocalDateTime;

public class ReservationUserComment {
	private long id;
	private long productId;
	private long reservationInfoId;
	private double score;
	private String comment;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public ReservationUserComment() {
		createDate = LocalDateTime.now();
	}

	public ReservationUserComment(long productId, long reservationInfoId, String comment, double score) {
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.comment = comment;
		this.score = score;
		createDate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "ReservationUserComment [id=" + id + ", productId=" + productId + ", reservationInfoId="
				+ reservationInfoId + ", score=" + score + ", comment=" + comment + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
