/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ReservationDisplayInfoDto;

/**
 * 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class ReservationResponseDto {
	private List<ReservationDisplayInfoDto> reservationDisplayInfos;
	private int totalCount;

	public ReservationResponseDto(List<ReservationDisplayInfoDto> reservationDisplayInfos, int count) {
		this.reservationDisplayInfos = reservationDisplayInfos;
		this.totalCount = count;
	}

	public List<ReservationDisplayInfoDto> getReservationDisplayInfos() {
		return reservationDisplayInfos;
	}

	public void setReservationDisplayInfos(List<ReservationDisplayInfoDto> reservationDisplayInfos) {
		this.reservationDisplayInfos = reservationDisplayInfos;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
