/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import com.nts.reservation.dto.reserve.ReserveRequest;

/**
 * reservation 등록에 사용
 */
public interface ReserveDao {
	/**
	 * api/reservation post로 새로운 예약 정보 등록
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public int insertReservation(ReserveRequest reserveRequest);

	/**
	 * api/reservation post로 새로운 예약의 가격 정보 등록
	 * @param reservePriceInfo
	 */
	public int insertReservationPrice(ReserveRequest reserveRequest);
}
