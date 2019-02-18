/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.reserve.ReserveResponse;

public interface ReserveService {
	/**
	 * reserve 페이지 로드시 상품 전시 정보와 가격을 가져온다. 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return ReserveResponse
	 */
	ReserveResponse getReserveResponse(int displayInfoId);
}
