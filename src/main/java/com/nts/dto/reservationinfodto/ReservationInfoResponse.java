/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.reservationinfodto;

import java.util.List;

public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;

	public ReservationInfoResponse() {}

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationInfo> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "ReservationInfoResponse [reservations=" + reservations + "]";
	}
}
