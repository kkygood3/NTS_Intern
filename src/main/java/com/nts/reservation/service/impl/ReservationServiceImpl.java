/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationInfoDao;
import com.nts.reservation.dao.ReservationInfoPriceDao;
import com.nts.reservation.dto.MyReservationDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.ReservationInfoPriceDto;
import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.service.ReservationService;
import com.nts.reservation.util.DateUtil;

/**
 * 예약 서비스
 * @author jinwoo.bae
 */
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;

	@Override
	@Transactional
	public void makeReservation(ReservationParamDto reservationParam) {
		ReservationInfoDto reservationInfo = new ReservationInfoDto();
		reservationInfo.setDisplayInfoId(reservationParam.getDisplayInfoId());
		reservationInfo.setProductId(reservationParam.getProductId());
		reservationInfo.setReservationName(reservationParam.getReservationName());
		reservationInfo.setReservationTel(reservationParam.getReservationTelephone());
		reservationInfo.setReservationEmail(reservationParam.getReservationEmail());
		reservationInfo.setReservationDate(reservationParam.getReservationDate());
		reservationInfo.setCancelFlag(false);
		int reservationInfoId = reservationInfoDao.insert(reservationInfo);

		for (ReservationInfoPriceDto reservationInfoPrice : reservationParam.getPrices()) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
			reservationInfoPriceDao.insert(reservationInfoPrice);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public MyReservationResponseDto getMyReservations(String reservationEmail) {
		List<MyReservationDto> myReservations = reservationInfoDao.selectMyReservations(reservationEmail);

		MyReservationResponseDto myReservationResponse = new MyReservationResponseDto();
		myReservationResponse.setTotalCount(myReservations.size());

		List<MyReservationDto> todoMyReservations = new ArrayList<MyReservationDto>();
		List<MyReservationDto> doneMyReservations = new ArrayList<MyReservationDto>();
		List<MyReservationDto> cancleMyReservations = new ArrayList<MyReservationDto>();

		for (MyReservationDto myReservation : myReservations) {
			if (myReservation.getCancelFlag()) {
				cancleMyReservations.add(myReservation);
			} else if (DateUtil.isAfterToday(myReservation.getReservationDate())) {
				doneMyReservations.add(myReservation);
			} else {
				todoMyReservations.add(myReservation);
			}
		}
		myReservationResponse.setTodoMyReservations(todoMyReservations);
		myReservationResponse.setDoneMyReservations(doneMyReservations);
		myReservationResponse.setCancleMyReservations(cancleMyReservations);
		myReservationResponse.setTodoCount(todoMyReservations.size());
		myReservationResponse.setDoneCount(doneMyReservations.size());
		myReservationResponse.setCancleCount(cancleMyReservations.size());
		return myReservationResponse;
	}

	@Override
	@Transactional
	public void cancleReservation(int reservationId) {
		reservationInfoDao.updateReservationToCancel(reservationId);
	}
}
