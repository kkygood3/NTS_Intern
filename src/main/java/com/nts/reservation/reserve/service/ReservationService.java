/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.service;

import java.util.List;

import org.springframework.web.HttpSessionRequiredException;

import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;

public interface ReservationService {

	ReservationResponse getReservationResponse(int reservationInfoId);

	ReservationResponse saveReservation(ReservationParam reservationParam);

	List<ReservationInfo> getReservationInfoResponse(String reservationEmail);

	ReservationResponse cancelReservation(int reservationInfoId, String sessionEmail) throws HttpSessionRequiredException;

}
