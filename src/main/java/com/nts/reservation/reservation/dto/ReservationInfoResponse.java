/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private ReservationInfoType reservationInfoType;
	private int count;

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationInfo> reservations) {
		this.reservations = reservations;
	}

	public ReservationInfoType getReservationInfoType() {
		return reservationInfoType;
	}

	public void setReservationInfoType(ReservationInfoType reservationInfoType) {
		this.reservationInfoType = reservationInfoType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
