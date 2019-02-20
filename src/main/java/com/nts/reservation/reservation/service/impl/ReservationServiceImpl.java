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
import com.nts.reservation.reservation.dto.ReservationPriceType;
import com.nts.reservation.reservation.dto.ReservationResponse;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ReservationServiceImpl {
	@Autowired
	ReservationDaoImpl reservationDaoImpl;

	@Autowired
	DisplayInfoDao displayInfoDaoImpl;

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

	public ReservationInfoResponse getReservationInfoResponse(String email, int displayInfoId) {
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();

		List<ReservationInfo> reservationInfoList = reservationDaoImpl.selectReservationInfo(email);
		for (ReservationInfo reservationInfo : reservationInfoList) {
			DisplayInfo displayInfo = displayInfoDaoImpl.selectDisplayInfo(displayInfoId);
			reservationInfo.setDisplayInfo(displayInfo);

			int totalPrice = reservationDaoImpl.selectTotalPrice(email, displayInfoId);
			reservationInfo.setTotalPrice(totalPrice);
		}
		reservationInfoResponse.setReservations(reservationInfoList);
		reservationInfoResponse.setSize(reservationInfoList.size());

		return reservationInfoResponse;
	}

	// PriceInfo
	public boolean insertReservation(String name, String telephone, String email, int displayInfoId, String priceInfo,
		String reservationDate) {
		ArgumentValidator.checkPersonInfo(name, telephone, email);
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		int reservationInfoId = reservationDaoImpl.insertReservation(name, telephone, email, displayInfoId,
			reservationDate);
		if (reservationInfoId == 0) {
			return false;
		}
		return true;
	}
}
