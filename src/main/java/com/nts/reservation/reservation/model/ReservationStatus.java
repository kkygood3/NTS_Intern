/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

public enum ReservationStatus {
	RESERVED(0), CANCELED(1), USED(2);

	private int statusCode;

	ReservationStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	public static ReservationStatus valueOf(int statusCode) {
		for (ReservationStatus reservationStatus : ReservationStatus.values()) {
			if (reservationStatus.statusCode == statusCode) {
				return reservationStatus;
			}
		}

		throw new IllegalArgumentException(
			String.format("statusCode : %d is not valid in ReservationStatus enumeration", statusCode));
	}

	public int getStatusCode() {
		return this.statusCode;
	}
}
