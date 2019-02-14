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

	/**
	 * @desc 예약 정보 입력.
	 * @param reservation
	 */
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

	/**
	 * @desc 예약 목록 조회
	 * @param email
	 * @return List<ReservationInfoDto>
	 */
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

	/**
	 * @desc 예약 갯수 조회
	 * @param email
	 * @return Integer
	 */
	@Override
	public Integer getReservationCount(String email) {
		return reservationDao.countReservationsByEmail(email);
	}

	/**
	 * @desc 예약 취소
	 * @param reservationId
	 * @return Boolean
	 */
	@Transactional(readOnly = false, rollbackFor = {SQLException.class})
	@Override
	public Boolean cancelReservation(Long reservationId) {
		int resultRows = reservationDao.updateCancelReservation(reservationId);
		return resultRows == 1;
	}
}
