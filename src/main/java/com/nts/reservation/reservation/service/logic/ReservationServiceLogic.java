/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistory;
import com.nts.reservation.reservation.model.ReservationPrice;
import com.nts.reservation.reservation.model.ReservationStatus;
import com.nts.reservation.reservation.service.ReservationService;

@Service
public class ReservationServiceLogic implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	@Override
	@Transactional
	public int addReservation(Reservation reservation) {

		int reservationInfoId = reservationDao.insertReservation(reservation);

		for (ReservationPrice reservationPrice : reservation.getReservationPriceList()) {
			if (reservationPrice.isReserved()) {
				reservationDao.insertReservationInfoPrice(reservationInfoId, reservationPrice);
			}
		}

		return reservationInfoId;
	}

	@Override
	public List<ReservationHistory> getReservationHistoryList(String reservationEmail) {
		return reservationDao.selectReservationHistoryList(reservationEmail);
	}

	@Override
	public void modifyReservationToCancel(int reservationId) {
		reservationDao.updateReservationCancelFlag(reservationId, ReservationStatus.Canceled.getStatusCode());
	}

}
