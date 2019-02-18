/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.MyReservationDto;
import com.nts.reservation.dto.param.ReservationParamDto;

/**
 * 예약 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ReservationService {
	void makeReservation(ReservationParamDto reservationParam);

	List<MyReservationDto> getMyReservations(String reservationEmail);
}
