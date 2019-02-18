/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.myreservation.MyReservationInfo;

public interface MyReservationService{
	/**
	 * myreservation 페이지 로드시 api/reservations 요청에 사용
	 * @param email
	 */
	List<MyReservationInfo> getMyReservationInfoList(String reservationEmail);
}
