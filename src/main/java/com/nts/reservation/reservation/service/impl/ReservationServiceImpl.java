/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.commons.debugPrinter.DebugPrinter;
import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.reservation.dao.impl.ReservationDaoImpl;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationPrice;
import com.nts.reservation.reservation.dto.ReservationPriceInfo;
import com.nts.reservation.reservation.dto.ReservationPriceType;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReserveRequest;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ReservationServiceImpl {
	@Autowired
	ReservationDaoImpl reservationDaoImpl;

	@Autowired
	DisplayInfoDao displayInfoDaoImpl;

	// 예약 조회하기
	// ReservationParam
	public ReservationResponse getReservationResponse(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		// 필드 ReservationDisplayInfo
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setReservationDisplayInfo(reservationDaoImpl.selectReservationDisplayInfo(displayInfoId));

		List<ReservationPrice> priceList = reservationDaoImpl.selectReservationPrice(displayInfoId);
		for (ReservationPrice price : priceList) {
			ReservationPriceType typeName = price.getPriceTypeName();
			String typeLabel = typeName.getLabel();
			price.setPriceTypeLabel(typeLabel);
		}
		// 필드 List<ReservationPrice>
		reservationResponse.setPrices(priceList);

		return reservationResponse;
	}

	// 나의 예약 조회하기
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail) {
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();

		List<ReservationInfo> reservationInfoList = reservationDaoImpl.selectReservationInfo(reservationEmail);
		for (ReservationInfo reservationInfo : reservationInfoList) {
			int displayInfoId = reservationInfo.getDisplayInfoId();

			DisplayInfo displayInfo = displayInfoDaoImpl.selectDisplayInfo(displayInfoId);
			reservationInfo.setDisplayInfo(displayInfo);

			int totalPrice = reservationDaoImpl.selectTotalPrice(reservationEmail, displayInfoId);
			reservationInfo.setTotalPrice(totalPrice);
		}
		reservationInfoResponse.setReservations(reservationInfoList);
		reservationInfoResponse.setSize(reservationInfoList.size());

		return reservationInfoResponse;
	}

	// 예약 하기
	public boolean insertReservationInfo(ReserveRequest reserveRequest) {
		ArgumentValidator.checkReserveRequest(reserveRequest);

		int reservationInfoId = reservationDaoImpl.insertReservationInfo(
			reserveRequest.getReservationName(),
			reserveRequest.getReservationTel(),
			reserveRequest.getReservationEmail(),
			reserveRequest.getDisplayInfoId(),
			reserveRequest.getReservationDate());
		if (reservationInfoId == 0) {
			DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 하기 실패 ( reservationInfoId == 0 )");
			return false;
		}

		List<ReservationPriceInfo> priceInfoList = reserveRequest.getReservationPriceInfoList();
		for (ReservationPriceInfo priceInfo : priceInfoList) {

			ReservationPriceType type = priceInfo.getType();
			int count = priceInfo.getCount();
			int displayInfoId = reserveRequest.getDisplayInfoId();

			int insertCompletePrice = reservationDaoImpl.insertReservationPrice(reservationInfoId, type, count,
				displayInfoId);
			if (insertCompletePrice == 0) {
				DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 하기 실패 ( insertCompletePrice == 0 )");
				return false;
			}
		}
		DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 하기 성공");

		return true;
	}

	// 나의 예약 취소하기
	public boolean cancelReservation(int reservationInfoId) {
		ArgumentValidator.checkReservationId(reservationInfoId);

		int cancelCompleteCount = reservationDaoImpl.cancelReservation(reservationInfoId);
		if (cancelCompleteCount == 0) {
			return false;
		}
		return true;
	}
}
