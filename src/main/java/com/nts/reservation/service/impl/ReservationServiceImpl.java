package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DetailDao;
import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.dto.reservation.ReservationParam;
import com.nts.reservation.dto.reservation.ReservationPrice;
import com.nts.reservation.service.ReservationService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private DetailDao detailDao;
	@Autowired
	private ReservationDao reservationDao;

	@Override
	public List<ReservationInfo> getReservations(String email) {
		List<ReservationInfo> reservations = reservationDao.selectResevations(email);
		for (ReservationInfo reservation : reservations) {
			reservation.setDisplayInfo(detailDao.selectDisplayInfo(reservation.getDisplayInfoId()));
		}
		return reservations;
	}

	@Override
	@Transactional(readOnly = false)
	public void addReservations(ReservationParam reservationParam) {
		Long reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		List<ReservationPrice> priceList = reservationParam.getPrices();
		for (ReservationPrice price : priceList) {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(price);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void cancelReservation(Long reservationId) {
		reservationDao.updateResevationCancelFlag(reservationId);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservationInfo getReservationByEmailAndRsvId(String email, Long reservationId) {
		return reservationDao.selectReservationByEmailAndRsvId(email, reservationId);
	}
}
