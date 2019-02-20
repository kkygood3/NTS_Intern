/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dao.impl.ReservationDaoImpl;
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

	public boolean postReservation(String name, String telephone, String email, int displayInfoId, String priceInfo,
		String reservationDate) {
		ArgumentValidator.checkPersonInfo(name, telephone, email);
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		ReservationResponse reservationResponse = new ReservationResponse();

		return true;

	}

}
