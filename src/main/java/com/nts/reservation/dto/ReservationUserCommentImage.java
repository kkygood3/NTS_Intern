package com.nts.reservation.dto;

public class ReservationUserCommentImage {
	private long id;
	private long reservationInfoId;
	private long reservationUserCommentId;
	private long fileId;

	public ReservationUserCommentImage(ReservationUserComment comment, FileInfo image) {
		this.reservationInfoId = comment.getReservationInfoId();
		this.reservationUserCommentId = comment.getId();
		this.fileId = image.getId();
	}
	
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

	public long getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
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
				+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + "]";
	}
}
