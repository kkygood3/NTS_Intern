/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

import java.util.List;

/**
 * detail 페이지 로드 시 /api/products/{displayInfoId} 요청에  detail 페이지의 정보를 담는 DTO
 */
public class ReserveResponse {
	private ReserveDisplayInfo reserveDisplayInfo;
	private List<ReservePrice> reservePrice;
	private String reservationDate;

	public ReserveResponse(ReserveDisplayInfo reserveDisplayInfo, List<ReservePrice> reservePrice,
		String reservationDate) {
		this.reserveDisplayInfo = reserveDisplayInfo;
		this.reservePrice = reservePrice;
		this.reservationDate = reservationDate;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public ReserveDisplayInfo getReserveDisplayInfo() {
		return reserveDisplayInfo;
	}

	public void setReserveDisplayInfo(ReserveDisplayInfo reserveDisplayInfo) {
		this.reserveDisplayInfo = reserveDisplayInfo;
	}

	public List<ReservePrice> getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(List<ReservePrice> reservePrice) {
		this.reservePrice = reservePrice;
	}
}
