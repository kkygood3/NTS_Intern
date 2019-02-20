/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.text.ParseException;

import com.nts.reservation.dto.myreservation.MyReservationResponse;

public interface MyReservationService {
	/**
	 * myreservation 페이지 로드시 api/reservations 요청에 사용
	 * @param email
	 * @throws ParseException 
	 */
	MyReservationResponse getMyReservationInfoList(String email) throws ParseException;

	/**
	 * 예약 목록에서 선택한 항목을 cancel 처리
	 * @param reservationInfoId
	 * @return cancel 성공시 TRUE
	 */
	boolean cancelMyReservation(Integer reservationInfoId);
}
