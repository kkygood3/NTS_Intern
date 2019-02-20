/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

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
import com.nts.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;
	@Autowired
	DisplayInfoDao displayInfoDao;

	@Override
	public List<ReservationInfo> getReservationInfosByReservationEmail(String reservationEmail) {
		List<Reservation> reservations = getReservationsByReservationEmail(reservationEmail);
		List<ReservationInfo> reservationInfos = new ArrayList<>();
		
		for(Reservation reservation : reservations) {
			ReservationInfo reservationInfo = new ReservationInfo();
			
			reservationInfo.setReservation(reservation);
			reservationInfo.setReservationPrices(getReservationPricesByReservationInfoId(reservation.getDisplayInfoId()));
			reservationInfo.setDisplayInfo(displayInfoDao.selectDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
			reservationInfos.add(reservationInfo);
		}
		return reservationInfos;
	}

	@Override
	public List<Reservation> getReservationsByReservationEmail(String reservationEmail) {
		return reservationDao.selectReservationsByReservationEmail(reservationEmail);
	}

	@Override
	public ReservationParam getReservationParamByReservationInfoId(int reservationInfoId) {
		return null;
	}

	@Override
	public List<ReservationPrice> getReservationPricesByReservationInfoId(int reservationInfoId) {
		return reservationDao.selectReservationPricesByReservationInfoId(reservationInfoId);
	}

	@Override
	public int modifyCancelFlagByReservationInfoId(int reservationInfoId) {
		return reservationDao.updateCancelFlagByReservationInfoId(reservationInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public int addReservation(ReservationParam reservationParam) {
		int key = reservationDao.insertReservation(reservationParam.getReservation());
		return addReservationPrices(key, reservationParam.getPrices());
	}

	@Override
	public int addReservationPrices(int key, List<ReservationPrice> prices) {
		int insertResult = 0;
		for(ReservationPrice price : prices) {
			insertResult += reservationDao.insertReservationPrice(key, price);
		}
		return insertResult;
	}
}
