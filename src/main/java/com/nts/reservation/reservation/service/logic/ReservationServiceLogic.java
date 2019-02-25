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

	private final ReservationDao reservationDao;

	@Autowired
	public ReservationServiceLogic(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	/** 
	 * 예매 정보 저장
	 */
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

	/**
	 * 예매 기록 조회
	 */
	@Override
	@IsEmpty
	public List<ReservationHistory> getReservationHistoryList(String reservationEmail) {
		return reservationDao.selectReservationHistoryList(reservationEmail);
	}

	/**
	 * 예매 취소, 사용자가 예매한 정보가아닌 예약상품 취소시 권한없음 exception 발생
	 */
	@Override
	public void modifyReservationToCancel(String reservationEmail, int reservationId) {
		int updateCount = reservationDao.updateReservationCancelFlag(reservationEmail, reservationId,
			ReservationStatus.CANCELED.getStatusCode());

		if (isUpdateZero(updateCount)) {
			throw new UnauthenticateException();
		}
	}

	/**
	 * 사용자가 예매했는지 조회 및 예매한 상품 이름 조회
	 */
	@Override
	public String getReservedProductDescription(String reservationEmail, int reservationId) {
		return reservationDao.selectReservedProductDescription(reservationEmail, reservationId);
	}

	private boolean isUpdateZero(int updateCount) {
		return updateCount == 0;
	}

	private boolean isNotInsert(int insertCount) {
		return insertCount == 0;
	}

}
