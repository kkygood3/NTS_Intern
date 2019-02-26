/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nts.reservation.common.model.Response;

public class ReservationHistoryResponse extends Response {

	private List<ReservationHistory> reservationHistoryList;

	public ReservationHistoryResponse(List<ReservationHistory> reservationHistoryList) {
		this.reservationHistoryList = reservationHistoryList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public List<ReservationHistory> getReservationHistoryList() {
		return reservationHistoryList;
	}

	public void setReservationHistoryList(List<ReservationHistory> reservationHistoryList) {
		this.reservationHistoryList = reservationHistoryList;
	}

}
