/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.service;

import com.nts.reservation.reserve.dto.ReservationInfoResponse;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;

public interface ReservationService {
	ReservationResponse insertAndReturnRerservationParam(ReservationParam reservationParam);

	ReservationResponse getReservationResponse(int reservationInfoId);

	ReservationInfoResponse getReservationInfoResponse(String reservationEmail);

	int cancelReservation(int reservationInfoId);
}
