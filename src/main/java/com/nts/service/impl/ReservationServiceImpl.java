/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.displayinfodao.DisplayInfoDao;
import com.nts.dao.reservationdao.ReservationDao;
import com.nts.dto.reservationdto.Reservation;
import com.nts.dto.reservationdto.ReservationParam;
import com.nts.dto.reservationdto.ReservationPrice;
import com.nts.dto.reservationinfodto.ReservationInfo;
import com.nts.exception.ExceptionValue;
import com.nts.exception.InvalidParameterException;
import com.nts.service.ReservationService;
import com.nts.util.Validator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Override
	public List<ReservationInfo> getReservationInfosByReservationEmail(String reservationEmail) {
		if (!Validator.emailValidation(reservationEmail)) {
			throw new InvalidParameterException("email", new ExceptionValue<String>(reservationEmail));
		}

		List<Reservation> reservations = getReservationsByReservationEmail(reservationEmail);
		List<ReservationInfo> reservationInfos = new ArrayList<>();

		for (Reservation reservation : reservations) {
			ReservationInfo reservationInfo = new ReservationInfo();

			reservationInfo.setReservation(reservation);
			reservationInfo
					.setReservationPrices(getReservationPricesByReservationInfoId(reservation.getDisplayInfoId()));
			reservationInfo
					.setDisplayInfo(displayInfoDao.selectDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
			reservationInfos.add(reservationInfo);
		}
		return reservationInfos;
	}

	@Override
	public List<Reservation> getReservationsByReservationEmail(String reservationEmail) {
		if (!Validator.emailValidation(reservationEmail)) {
			throw new InvalidParameterException("email", new ExceptionValue<String>(reservationEmail));
		}

		return reservationDao.selectReservationsByReservationEmail(reservationEmail);
	}

	@Override
	public List<ReservationPrice> getReservationPricesByReservationInfoId(int reservationInfoId) {
		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}

		return reservationDao.selectReservationPricesByReservationInfoId(reservationInfoId);
	}

	@Override
	public int modifyCancelFlag(int reservationInfoId, boolean cancelFlag) {
		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}

		return reservationDao.updateCancelFlag(reservationInfoId, cancelFlag);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public int addReservation(ReservationParam reservationParam) {
		
		if(!Validator.reservationValidation(reservationParam.getReservation())) {
			throw new InvalidParameterException("email",
					new ExceptionValue<String>(reservationParam.getReservation().toString()));
		}

		int key = reservationDao.insertReservation(reservationParam.getReservation());
		return addReservationPrices(key, reservationParam.getPrices());
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public int addReservationPrices(int key, List<ReservationPrice> prices) {
		if (key <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(key));
		}

		int insertResult = 0;
		for (ReservationPrice price : prices) {
			insertResult += reservationDao.insertReservationPrice(key, price);
		}
		return insertResult;
	}
}
