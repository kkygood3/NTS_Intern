/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service;

import java.util.List;

import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistory;

public interface ReservationService {

	int addReservation(Reservation reservation);

	List<ReservationHistory> getReservationHistoryList(String reservationEmail);

	void modifyReservationToCancel(String reservationEmail, int reservationId);
}
