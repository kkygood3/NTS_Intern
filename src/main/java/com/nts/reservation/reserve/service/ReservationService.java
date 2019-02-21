/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.service;

import java.util.List;

import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;

public interface ReservationService {

	ReservationResponse getReservationResponse(int reservationInfoId);

	List<ReservationInfo> getReservationInfoResponse(String reservationEmail);

	int cancelReservation(int reservationInfoId);

	int insertReservation(ReservationParam reservationParam);

}
