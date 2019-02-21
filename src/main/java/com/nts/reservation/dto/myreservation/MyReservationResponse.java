/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.myreservation;

import java.util.List;

public class MyReservationResponse {
	List<MyReservationInfo> reservationList;
	ReservationType reservationType;
	Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<MyReservationInfo> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<MyReservationInfo> reservationList) {
		this.reservationList = reservationList;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

}
