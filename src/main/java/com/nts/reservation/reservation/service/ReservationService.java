/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationInfoType;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReserveRequest;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ReservationService {
	ReservationResponse getReservationResponse(int displayInfoId);

	ReservationInfoResponse getReservationInfoResponse(String reservationEmail,
		ReservationInfoType reservationInfoType, int start, int limit);

	boolean insertReservationInfo(ReserveRequest reserveRequest);

	boolean cancelReservation(int reservationInfoId);
}
