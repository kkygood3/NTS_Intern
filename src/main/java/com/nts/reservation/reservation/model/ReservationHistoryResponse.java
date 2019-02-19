/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

import java.util.List;

public class ReservationHistoryResponse {

	private List<ReservationHistory> reservationHistoryList;

	public ReservationHistoryResponse(List<ReservationHistory> reservationHistoryList) {
		this.reservationHistoryList = reservationHistoryList;
	}

	@Override
	public String toString() {
		return "ReservationHistoryResponse [reservationHistoryList=" + reservationHistoryList + "]";
	}

	public List<ReservationHistory> getReservationHistoryList() {
		return reservationHistoryList;
	}

	public void setReservationHistoryList(List<ReservationHistory> reservationHistoryList) {
		this.reservationHistoryList = reservationHistoryList;
	}

}
