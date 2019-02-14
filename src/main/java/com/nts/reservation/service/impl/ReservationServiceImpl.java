/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;

	@Override
	public void setReservation(HttpSession session, Reservation reservationInfo) {
		session.setAttribute("userEmail", reservationInfo.getReservationEmail());

		reservationDao.setReservation(reservationInfo);

		List<ReservationPrice> reservationInfoPrices = reservationInfo.getPrices();
		for (ReservationPrice reservationInfoPrice : reservationInfoPrices) {
			reservationInfoPrice.setReservationInfoId(reservationDao.getReservationInfoId(reservationInfo));
			reservationDao.setReservationPrice(reservationInfoPrice);
		}
	}

	@Override
	public List<Reservation> getAvailableReservations(String userEmail) {
		return reservationDao.getReservations(userEmail, 0);
	}

	@Override
	public List<Reservation> getCanceledReservations(String userEmail) {
		return reservationDao.getReservations(userEmail, 1);
	}
}
