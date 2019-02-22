/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.service;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationResponse;

public interface ReservationService {
	ReservationInfoResponse getReservationInfoResponse(String reservationEmail);

	ReservationResponse getReservationResponse(ReservationParam reservationParam);

	boolean postReserve(ReservationParam reservationParam);

	boolean updateReserve(int reservationInfoId, String reservationEmail);
}
