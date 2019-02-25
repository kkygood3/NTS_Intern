/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.common.annotation.IsEmpty;
import com.nts.reservation.common.exception.InternalServerErrorException;
import com.nts.reservation.common.exception.UnauthenticateException;
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
		try {
			int reservationInfoId = reservationDao.insertReservation(reservation);

			for (ReservationPrice reservationPrice : reservation.getReservationPriceList()) {
				if (reservationPrice.isReserved()) {
					if (isNotInsert(reservationDao.insertReservationInfoPrice(reservationInfoId, reservationPrice))) {
						throw new InternalServerErrorException("reservation data error");
					}
				}
			}
			return reservationInfoId;
		} catch (DataIntegrityViolationException e) {
			throw new InternalServerErrorException("reservation data integrity violation");
		}
	}

	@Override
	@IsEmpty
	public List<ReservationHistory> getReservationHistoryList(String reservationEmail) {
		return reservationDao.selectReservationHistoryList(reservationEmail);
	}

	@Override
	public void modifyReservationToCancel(String reservationEmail, int reservationId) {
		int updateCount = reservationDao.updateReservationCancelFlag(reservationEmail, reservationId,
			ReservationStatus.CANCELED.getStatusCode());

		if (isUpdateZero(updateCount)) {
			throw new UnauthenticateException();
		}
	}

	private boolean isUpdateZero(int updateCount) {
		return updateCount == 0;
	}

	private boolean isNotInsert(int insertCount) {
		return insertCount == 0;
	}

}
