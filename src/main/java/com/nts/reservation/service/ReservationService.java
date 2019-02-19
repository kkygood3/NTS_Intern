/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;

/**
 * 예약 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ReservationService {
	void makeReservation(ReservationParamDto reservationParam);

	MyReservationResponseDto getMyReservations(String reservationEmail);
}
