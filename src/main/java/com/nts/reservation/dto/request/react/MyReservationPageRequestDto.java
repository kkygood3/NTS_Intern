/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.request.react;

import java.util.List;

import com.nts.reservation.dto.ReservationInfoDto;

/**
 * @author 육성렬
 */
public class MyReservationPageRequestDto {
	private String email;
	private List<ReservationInfoDto> reservations;
	private Integer size;

	public MyReservationPageRequestDto(String email, List<ReservationInfoDto> reservations, Integer size) {
		super();
		this.email = email;
		this.reservations = reservations;
		this.size = size;
	}

	public String getEmail() {
		return email;
	}

	public List<ReservationInfoDto> getReservations() {
		return reservations;
	}

	public Integer getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "MyReservationPageRequestDto [email=" + email + ", reservations=" + reservations + ", size=" + size
			+ "]";
	}

}
