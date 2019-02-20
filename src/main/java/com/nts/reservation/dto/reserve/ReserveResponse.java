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
	ReserveDisplayInfo reserveDisplayInfo;
	List<ReservePrice> reservePrice;

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
