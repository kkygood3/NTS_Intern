/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.displayinfo.dao.DisplayInfoDao;
import com.nts.reservation.displayinfo.dto.DisplayInfo;
import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationPrice;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReservationResponseData;
import com.nts.reservation.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDaoImpl;

	@Autowired
	DisplayInfoDao displayInfoDaoImpl;

	@Override
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail) {
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();

		List<ReservationInfo> reservations = reservationDaoImpl.getReservationInfos(reservationEmail);
		reservations.forEach(reservation -> {
			int displayInfoId = reservation.getDisplayInfoId();

			DisplayInfo displayInfo = displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
			int reservationTotalPrice = reservationDaoImpl.getTotalPrice(reservationEmail, displayInfoId);

			reservation.setDisplayInfo(displayInfo);
			reservation.setTotalPrice(reservationTotalPrice);
		});
		int reservationSize = reservationDaoImpl.getReservationSize(reservationEmail);

		reservationInfoResponse.setReservations(reservations);
		reservationInfoResponse.setSize(reservationSize);

		return reservationInfoResponse;
	}

	@Override
	public ReservationResponse getReservationResponse(ReservationParam reservationParam) {
		ReservationResponse reservationResponse = new ReservationResponse();

		int productId = reservationParam.getProductId();

		ReservationResponseData reservationResponseData = reservationDaoImpl
			.getReservationResponseData(reservationParam);
		List<ReservationPrice> reservationPrices = reservationDaoImpl.getReservationPrices(productId);

		reservationResponse.setReservationResponseData(reservationResponseData);
		reservationResponse.setPrices(reservationPrices);

		return reservationResponse;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean postReserve(ReservationParam reservationParam) {
		int reservationInfoId = reservationDaoImpl.insertReservation(reservationParam.getReservationName(),
			reservationParam.getReservationTelephone(), reservationParam.getReservationEmail(),
			reservationParam.getDisplayInfoId());

		if (reservationInfoId == 0) {
			return false;
		}

		reservationParam.getPrices().forEach(price -> {
			Integer reservationInfoPriceId = reservationDaoImpl.insertReservationPrice(
				price.getProductPriceId(),
				reservationInfoId, price.getCount());

			if (reservationInfoPriceId == null || reservationInfoPriceId < 0) {
				throw new IllegalArgumentException("insertReservationPrice Failed");
			}
		});

		return true;
	}

}
