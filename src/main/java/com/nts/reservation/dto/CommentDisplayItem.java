package com.nts.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDisplayItem {
	private String comment;
	private double score;
	private String reservationEmail;
	private LocalDateTime reservationDate;
	private long commentImageId;

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

	public long getCommentImageId() {
		return commentImageId;
	}

	public void setCommentImageId(long commentImageId) {
		this.commentImageId = commentImageId;
	}

	@Override
	public String toString() {
		return "CommentDisplayItem [comment=" + comment + ", score=" + score + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", commentImageId=" + commentImageId + "]";
	}
}
