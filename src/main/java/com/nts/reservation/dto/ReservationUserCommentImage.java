package com.nts.reservation.dto;

public class ReservationUserCommentImage {
	private long id;
	private long reservationInfoId;
	private long reservationUserComment;
	private long fileId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public long getReservationUserComment() {
		return reservationUserComment;
	}
	public void setReservationUserComment(long reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "ReservationUserCommentImage [id=" + id + ", reservationInfoId=" + reservationInfoId
			+ ", reservationUserComment=" + reservationUserComment + ", fileId=" + fileId + "]";
	}
	
	
}
