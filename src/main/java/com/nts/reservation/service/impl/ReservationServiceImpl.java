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
import com.nts.reservation.dto.ReservationDisplayInfoDto;
import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.primitive.ReservationInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoPriceDto;
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
	public MyReservationResponseDto getMyReservationResponse(String reservationEmail) {
		List<ReservationDisplayInfoDto> reservationDisplayInfos = reservationInfoDao
			.selectReservationDisplayInfos(reservationEmail);

		MyReservationResponseDto myReservationResponse = new MyReservationResponseDto();
		myReservationResponse.setTotalCount(reservationDisplayInfos.size());

		List<ReservationDisplayInfoDto> todoReservationDisplayInfos = new ArrayList<ReservationDisplayInfoDto>();
		List<ReservationDisplayInfoDto> doneReservationDisplayInfos = new ArrayList<ReservationDisplayInfoDto>();
		List<ReservationDisplayInfoDto> cancleReservationDisplayInfos = new ArrayList<ReservationDisplayInfoDto>();

		for (ReservationDisplayInfoDto reservationDisplayInfo : reservationDisplayInfos) {
			if (reservationDisplayInfo.getCancelFlag()) {
				cancleReservationDisplayInfos.add(reservationDisplayInfo);
			} else if (DateUtil.isAfterToday(reservationDisplayInfo.getReservationDate())) {
				doneReservationDisplayInfos.add(reservationDisplayInfo);
			} else {
				todoReservationDisplayInfos.add(reservationDisplayInfo);
			}
		}
		myReservationResponse.setTodoReservationDisplayInfos(todoReservationDisplayInfos);
		myReservationResponse.setDoneReservationDisplayInfos(doneReservationDisplayInfos);
		myReservationResponse.setCancleReservationDisplayInfos(cancleReservationDisplayInfos);
		myReservationResponse.setTodoCount(todoReservationDisplayInfos.size());
		myReservationResponse.setDoneCount(doneReservationDisplayInfos.size());
		myReservationResponse.setCancleCount(cancleReservationDisplayInfos.size());
		return myReservationResponse;
	}

	@Override
	@Transactional
	public void cancleReservation(int reservationId) {
		reservationInfoDao.updateReservationToCancel(reservationId);
	}
}
