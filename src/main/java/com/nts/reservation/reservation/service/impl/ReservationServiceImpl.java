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
	private ReservationDao reservationDaoImpl;

	@Autowired
	private DisplayInfoDao displayInfoDaoImpl;

	/**
	 * 예약 Email을 받아서 ReservationInfo의 정보를 가져와서 예약 정보를 반환
	 * @param reservationEmail
	 */
	@Override
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail) {
		List<ReservationInfo> reservations = reservationDaoImpl.getReservationInfos(reservationEmail);

		// ReservationInfo의 displayInfo와 TotalPrice을 설정하는 반복문
		for (ReservationInfo reservation : reservations) {
			fetchReservationInfo(reservation, reservationEmail);
		}

		int reservationSize = reservationDaoImpl.getReservationSize(reservationEmail);

		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		reservationInfoResponse.setReservations(reservations);
		reservationInfoResponse.setSize(reservationSize);

		return reservationInfoResponse;
	}

	private ReservationInfo fetchReservationInfo(ReservationInfo reservation, String Email) {
		int displayInfoId = reservation.getDisplayInfoId();
		int reservationInfoId = reservation.getReservationInfoId();
		int productId = reservation.getProductId();

		DisplayInfo displayInfo = displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
		int reservationTotalPrice = reservationDaoImpl.getTotalPrice(Email, productId, reservationInfoId);

		reservation.setDisplayInfo(displayInfo);
		reservation.setTotalPrice(reservationTotalPrice);

		return reservation;
	}

	/**
	 * 예약 파라미터들을 입력 받아 예약 정보와 가격 정보를 추가하여 반환
	 * @param reservationParam
	 */
	@Override
	public ReservationResponse getReservationResponse(ReservationParam reservationParam) {
		int productId = reservationParam.getProductId();

		ReservationResponseData reservationResponseData = reservationDaoImpl
			.getReservationResponseData(reservationParam);
		List<ReservationPrice> reservationPrices = reservationDaoImpl.getReservationPrices(productId);

		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setReservationResponseData(reservationResponseData);
		reservationResponse.setPrices(reservationPrices);

		return reservationResponse;
	}

	@Override
	@Transactional
	public boolean postReserve(ReservationParam reservationParam) {
		int reservationInfoId = reservationDaoImpl.insertReservation(reservationParam);

		if (reservationInfoId == 0) {
			return false;
		}

		for (ReservationPrice price : reservationParam.getPrices()) {
			if (reservationDaoImpl.insertReservationPrice(price.getProductPriceId(),
				reservationInfoId, price.getCount()) < 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean updateReserve(int reservationInfoId, String reservationEmail) {
		return (reservationDaoImpl.updateReservation(reservationInfoId, reservationEmail) > 0);
	}

}
