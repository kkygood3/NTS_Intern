package com.nts.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDisplayItem {
	private String comment;
	private double score;
	private String reservationEmail;
	private LocalDateTime reservationDate;
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
		return reservationDate.format(DateTimeFormatter.ofPattern("yyyy.M.d."));
	}

	public void setReservationDate(LocalDateTime reservationDate) {
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
				+ reservationEmail + ", reservationDate=" + reservationDate + ", saveFileName=" + saveFileName + "]";
	}
}
