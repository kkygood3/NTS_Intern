/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation;

import com.nts.dto.reservation.ReservationParameter;

import java.text.ParseException;

import javax.naming.NoPermissionException;

import com.nts.dto.reservation.Reservation;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.exception.DisplayInfoNullException;

public interface ReservationService {
	ReservationInfos getReservationInfoByReservationEmail(String reservationEmail) throws DisplayInfoNullException;

	int addReservation(ReservationParameter reservationParameter) throws ParseException;

	int cancelReservation(long reservationId, String reservationEmail);

	Reservation getReservedInfo(String reservationEmail, int reservationInfoId) throws NoPermissionException;
}
