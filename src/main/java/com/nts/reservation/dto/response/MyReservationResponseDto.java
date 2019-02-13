/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ReservationInfoDto;

/**
 * @author 육성렬
 */
public class MyReservationResponseDto {
	private List<ReservationInfoDto> reservations;
	private Integer size;

	public MyReservationResponseDto(List<ReservationInfoDto> reservations, Integer size) {
		super();
		this.reservations = reservations;
		this.size = size;
	}

	public List<ReservationInfoDto> getReservations() {
		return reservations;
	}

	public Integer getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "MyReservationResponseDto [reservations=" + reservations + ", size=" + size + "]";
	}

}
