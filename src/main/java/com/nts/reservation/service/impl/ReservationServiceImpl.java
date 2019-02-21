/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import static com.nts.reservation.constant.ReservationStatusType.*;

import java.util.Collections;
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
import com.nts.reservation.dto.response.ReservationResponseDto;
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
	public MyReservationResponseDto getMyReservationResponse(String reservationEmail, int start, int limit) {

		ReservationResponseDto todoReservationResponse = getReservationResponse(reservationEmail, TODO, start, limit);
		ReservationResponseDto doneReservationResponse = getReservationResponse(reservationEmail, DONE, start, limit);
		ReservationResponseDto cancelReservationResponse = getReservationResponse(reservationEmail, CANCEL, start,
			limit);

		return new MyReservationResponseDto(todoReservationResponse, doneReservationResponse,
			cancelReservationResponse);
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

	@Override
	@Transactional
	public ReservationResponseDto getReservationResponse(String reservationEmail,
		ReservationStatusType status, int start, int limit) {
		int count = reservationMapper.selectReservationCountByStatus(reservationEmail, status);
		if (count <= start) {
			return new ReservationResponseDto(Collections.<ReservationDisplayInfoDto>emptyList(), count);
		}
		List<ReservationDisplayInfoDto> reservationDisplayInfos = reservationMapper
			.selectReservationDisplayInfos(reservationEmail, status, start, limit);

		return new ReservationResponseDto(reservationDisplayInfos, count);
	}
}
