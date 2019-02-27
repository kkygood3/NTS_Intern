package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.dto.reservation.ReservationParam;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public interface ReservationService {
	List<ReservationInfo> getReservations(String email);

	void addReservations(ReservationParam resInput);

	void cancelReservation(Long reservationId);

	ReservationInfo getReservationByEmailAndRsvId(String email, Long reservationId);
}
