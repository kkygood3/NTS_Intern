/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.constant.ReservationStatusType;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.dto.response.ReservationResponseDto;

/**
 * 예약 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ReservationService {
	void makeReservation(ReservationParamDto reservationParam);

	MyReservationResponseDto getMyReservationResponse(String reservationEmail, PageDto page);

	void cancleReservation(int reservationId);

	ReservationResponseDto getReservationResponse(String reservationEmail, ReservationStatusType valueOf,
		PageDto page);

}
