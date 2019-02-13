/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationPriceRequestDto;
import com.nts.reservation.dto.request.ReservationRequestDto;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	DisplayInfoDao displayDao;

	@Transactional(readOnly = false, rollbackFor = {SQLException.class})
	@Override
	public void addReservation(ReservationRequestDto reservation) {
		long reservationInfoId = reservationDao.insertReservation(reservation);
		List<ReservationPriceRequestDto> priceList = reservation.getPrices();
		for (ReservationPriceRequestDto price : priceList) {
			price.setReservationInfoId(reservationInfoId);
		}
		reservationDao.insertReservationPrices(priceList);
	}

	@Override
	public List<ReservationInfoDto> getReservationList(String email) {
		List<ReservationInfoDto> list = reservationDao.selectReservationsByEmail(email);
		for (ReservationInfoDto item : list) {
			Long id = item.getDisplayInfoId();
			DisplayInfoDto display = displayDao.selectDisplayInfo(id);
			item.setDisplayInfo(display);
		}
		return list;
	}

	@Override
	public Integer getReservationCount(String email) {
		return reservationDao.countReservationsByEmail(email);
	}

	@Transactional(readOnly = false, rollbackFor = {SQLException.class})
	@Override
	public Boolean cancelReservation(Long reservationId) {
		int resultRows = reservationDao.updateCancelReservation(reservationId);
		return resultRows == 1;
	}
}
