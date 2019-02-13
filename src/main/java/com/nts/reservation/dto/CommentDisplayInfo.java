package com.nts.reservation.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentDisplayInfo {
	private String comment;
	private double score;
	private String reservationEmail;
	private Timestamp reservationDate;
	private String saveFileName;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getScore() {
		return String.format("%.1f", score);
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationDate() {
		return new SimpleDateFormat("yyyy.M.d.").format(reservationDate);
	}

	public void setReservationDate(Timestamp reservationDate) {
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
		return "ReservationUserCommentDisplay [comment=" + comment + ", score=" + score + ", reservationEmail="
			+ reservationEmail
			+ ", reservationDate=" + reservationDate + ", saveFileName=" + saveFileName + "]";
	}
}
