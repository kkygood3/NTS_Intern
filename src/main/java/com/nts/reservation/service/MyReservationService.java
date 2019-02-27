/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.myreservation.MyReservationResponse;
import com.nts.reservation.dto.myreservation.ReservationType;

public interface MyReservationService {
	/**
	 * myreservation 페이지 로드시 api/reservations 요청에 사용
	 * @param email
	 * @param reservationType
	 * @param start
	 * @param pagingLimit
	 */
	MyReservationResponse getMyReservationResponse(String email, ReservationType reservationType, Integer start,
		Integer pagingLimit);

	/**
	 * 예약 목록에서 선택한 항목을 cancel 처리
	 * @param reservationInfoId
	 * @param email
	 * @return cancel 성공시 TRUE
	 */
	boolean cancelMyReservation(Integer reservationInfoId, String email);
}
