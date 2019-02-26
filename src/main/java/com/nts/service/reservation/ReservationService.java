/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation;

import com.nts.dto.reservation.ReservationParameter;
import com.nts.exception.NotFoundException;

import javax.naming.NoPermissionException;

import com.nts.dto.reservation.Reservation;
import com.nts.dto.reservation.ReservationInfos;

public interface ReservationService {
	ReservationInfos getReservationInfoByReservationEmail(String reservationEmail) throws NotFoundException;

	int addReservation(ReservationParameter reservationParameter);

	int cancelReservation(long reservationId, String reservationEmail) throws NoPermissionException;

	Reservation getReservedInfo(String reservationEmail, int reservationInfoId) throws NoPermissionException;
}
