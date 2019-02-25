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
import com.nts.reservation.dto.param.PageDto;
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

	/**
	 * 나의예약 페이지용
	 * 예정,완료,취소 예약들과 총개수들을 조합해 가져옵니다.
	 */
	@Override
	@Transactional(readOnly = true)
	public MyReservationResponseDto getMyReservationResponse(String reservationEmail, PageDto page) {

		ReservationResponseDto todoReservationResponse = getReservationResponse(reservationEmail, TODO, page);
		ReservationResponseDto doneReservationResponse = getReservationResponse(reservationEmail, DONE, page);
		ReservationResponseDto cancelReservationResponse = getReservationResponse(reservationEmail, CANCEL, page);

		return new MyReservationResponseDto(todoReservationResponse, doneReservationResponse,
			cancelReservationResponse);
	}

	/**
	 * 예약하기
	 */
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
		}

		reservationMapper.insertReservationInfoPrices(reservationParam.getPrices());
	}

	/**
	 * 예약 취소
	 */
	@Override
	@Transactional
	public void cancelReservation(int reservationId) {
		reservationMapper.updateReservationToCancel(reservationId);
	}

	/**
	 * 예약상태값(예정,완료,취소)에 따라 에약리스트들과 총개수를 가져옵니다. 
	 */
	@Override
	@Transactional
	public ReservationResponseDto getReservationResponse(String reservationEmail,
		ReservationStatusType status, PageDto page) {
		int count = reservationMapper.selectReservationCountByStatus(reservationEmail, status);
		if (count <= page.getStart()) {
			return new ReservationResponseDto(Collections.<ReservationDisplayInfoDto>emptyList(), count);
		}
		List<ReservationDisplayInfoDto> reservationDisplayInfos = reservationMapper
			.selectReservationDisplayInfos(reservationEmail, status, page);

		return new ReservationResponseDto(reservationDisplayInfos, count);
	}
}
