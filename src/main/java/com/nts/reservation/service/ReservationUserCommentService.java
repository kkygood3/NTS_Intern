package com.nts.reservation.service;

public interface ReservationUserCommentService {
	public double getAverageScore(long productId);
	public int getCount(long productId);
}
