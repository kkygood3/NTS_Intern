/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.DetailProductDao;
import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.dto.ReservedItem;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	DetailProductDao detailProductDao;

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
		List<Reservation> availableReservations = reservationDao.getReservations(userEmail, 0);
		return availableReservations;
	}

	@Override
	public List<Reservation> getCanceledReservations(String userEmail) {
		List<Reservation> canceledReservations = reservationDao.getReservations(userEmail, 1);
		return canceledReservations;
	}

	@Override
	public List<ReservedItem> getReservedItems(String userEmail, int cancelFlag) {
		List<ReservedItem> availableReservedItems = new ArrayList<>();

		List<Reservation> availableReservations = reservationDao.getReservations(userEmail, cancelFlag);
		for (Reservation reservation : availableReservations) {
			ReservedItem reservedItem = new ReservedItem();

			reservedItem.setReservation(reservation);
			reservedItem.setTotalPrice(reservationDao.getTotalPrice(reservation.getId()));
			reservedItem.setDisplayInfo(detailProductDao.getDisplayInfo(reservation.getDisplayInfoId()));

			availableReservedItems.add(reservedItem);
		}

		return availableReservedItems;
	}

	@Override
	public void cancelReservation(Integer reservationInfoId) {
		reservationDao.cancelReservations(reservationInfoId);
	}
}