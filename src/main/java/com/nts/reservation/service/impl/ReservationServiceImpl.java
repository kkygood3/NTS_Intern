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
@Transactional(readOnly = false)
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	DetailDao detailDao;
	@Autowired
	ReservationDao reservationDao;

	@Override
	@Transactional(readOnly = true)
	public List<ReservationInfo> getReservations(String email) {
		List<ReservationInfo> reservationList = reservationDao.selectResevations(email);
		for (ReservationInfo rsv : reservationList) {
			rsv.setDisplayInfo(detailDao.selectDisplayInfo(rsv.getDisplayInfoId()));
		}
		return reservationList;
	}

	@Override
	public void addReservations(ReservationParam input) {
		Long reservationInfoId = reservationDao.insertReservationInfo(input);
		List<ReservationPrice> priceList = input.getPrices();
		for (ReservationPrice p : priceList) {
			p.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(p);
		}
	}

	@Override
	public void cancelReservation(Long reservationId) {
		reservationDao.updateResevationCancelFlag(reservationId);
	}
}
