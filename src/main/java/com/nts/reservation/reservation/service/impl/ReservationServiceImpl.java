/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setReservationDisplayInfo(reservationDaoImpl.selectReservationDisplayInfo(displayInfoId));

		List<ReservationPrice> priceList = reservationDaoImpl.selectReservationPrice(displayInfoId);
		for (ReservationPrice price : priceList) {
			ReservationPriceType typeName = price.getPriceTypeName();
			String typeLabel = typeName.getLabel();
			price.setPriceTypeLabel(typeLabel);
		}
		reservationResponse.setPrices(priceList);

		return reservationResponse;
	}

	// (테스트 완료) 나의 예약 조회하기
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
	// PriceInfo
	public boolean insertReservationInfo(ReserveRequest reserveRequest) {
		ArgumentValidator.checkReserveRequest(reserveRequest);

		//		int reservationInfoId = reservationDaoImpl.insertReservationInfo(
		//			reserveRequest.getReservationName(),
		//			reserveRequest.getReservationTel(),
		//			reserveRequest.getReservationEmail(),
		//			reserveRequest.getDisplayInfoId(),
		//			reserveRequest.getReservationDate());
		//		if (reservationInfoId == 0) {
		//			System.out.println("예약 실패 ( reservationInfoId == 0 )");
		//			return false;
		//		}
		int reservationInfoId = 21;

		List<ReservationPriceInfo> priceInfoList = reserveRequest.getReservationPriceInfoList();
		for (ReservationPriceInfo priceInfo : priceInfoList) {
			ReservationPriceType type = priceInfo.getType();
			int count = priceInfo.getCount();
			int displayInfoId = reserveRequest.getDisplayInfoId();
			//
			//			System.out.println("[ReservationServiceImpl.java] reservationInfoId : " + reservationInfoId);
			//
			//			System.out.println("[ReservationServiceImpl.java] type : " + type);
			//			System.out.println("[ReservationServiceImpl.java] count : " + count);
			//			System.out.println("[ReservationServiceImpl.java] displayInfoId : " + displayInfoId);

			int insertCompletePrice = reservationDaoImpl.insertReservationPrice(type, count,
				displayInfoId, reservationInfoId);
			if (insertCompletePrice == 0) {
				System.out.println("예약 실패 ( insertCompletePrice == 0 )");
				return false;
			}
		}
		System.out.println("예약 성공");
		return true;
	}

	// (테스트 완료) 예약 취소하기
	public boolean cancelReservation(int reservationInfoId) {
		ArgumentValidator.checkReservationId(reservationInfoId);

		int cancelCompleteCount = reservationDaoImpl.cancelReservation(reservationInfoId);
		if (cancelCompleteCount == 0) {
			return false;
		}
		return true;
	}
}
