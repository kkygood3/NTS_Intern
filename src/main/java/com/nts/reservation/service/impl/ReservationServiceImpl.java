/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.constant.ReservationStatusType;
import com.nts.reservation.dto.ReservationDisplayInfoDto;
import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.primitive.ReservationInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoPriceDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.mapper.ReservationMapper;
import com.nts.reservation.service.ReservationService;

/**
 * 예약 서비스
 * @author jinwoo.bae
 */
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationMapper reservationMapper;

	@Override
	@Transactional(readOnly = true)
	public MyReservationResponseDto getMyReservationResponse(String reservationEmail, int limit) {
		MyReservationResponseDto myReservationResponse = new MyReservationResponseDto();

		List<ReservationDisplayInfoDto> todoReservationDisplayInfos = reservationMapper
			.selectReservationDisplayInfos(reservationEmail, limit, ReservationStatusType.TODO);
		List<ReservationDisplayInfoDto> doneReservationDisplayInfos = reservationMapper
			.selectReservationDisplayInfos(reservationEmail, limit, ReservationStatusType.DONE);
		List<ReservationDisplayInfoDto> cancleReservationDisplayInfos = reservationMapper
			.selectReservationDisplayInfos(reservationEmail, limit, ReservationStatusType.CANCLE);

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
	public void makeReservation(ReservationParamDto reservationParam) {
		ReservationInfoDto reservationInfo = new ReservationInfoDto();
		reservationInfo.setDisplayInfoId(reservationParam.getDisplayInfoId());
		reservationInfo.setProductId(reservationParam.getProductId());
		reservationInfo.setReservationName(reservationParam.getReservationName());
		reservationInfo.setReservationTel(reservationParam.getReservationTelephone());
		reservationInfo.setReservationEmail(reservationParam.getReservationEmail());
		reservationInfo.setReservationDate(reservationParam.getReservationDate());
		reservationInfo.setCancelFlag(false);
		reservationMapper.insertReservationInfo(reservationInfo);

		for (ReservationInfoPriceDto reservationInfoPrice : reservationParam.getPrices()) {
			reservationInfoPrice.setReservationInfoId(reservationInfo.getId());
			reservationMapper.insertReservationInfoPrice(reservationInfoPrice);
		}
	}

	@Override
	@Transactional
	public void cancleReservation(int reservationId) {
		reservationMapper.updateReservationToCancel(reservationId);
	}
}
