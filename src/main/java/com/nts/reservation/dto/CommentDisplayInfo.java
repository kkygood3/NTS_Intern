package com.nts.reservation.dto;

import java.util.Date;

public class CommentDisplayInfo {
	private String comment;
	private double score;
	private String email;
	private Date reservationDate;
	private String saveFileName;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "ReservationUserCommentDisplay [comment=" + comment + ", score=" + score + ", email=" + email
			+ ", reservationDate=" + reservationDate + ", saveFileName=" + saveFileName + "]";
	}
}
