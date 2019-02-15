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
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	DetailProductDao detailProductDao;

	@Override
	public void makeReservation(HttpSession session, Reservation reservationInfo) {
		session.setAttribute("userEmail", reservationInfo.getReservationEmail());

		reservationDao.setReservation(reservationInfo);

		List<ReservationPrice> reservationInfoPrices = reservationInfo.getPrices();
		for (ReservationPrice reservationInfoPrice : reservationInfoPrices) {
			reservationInfoPrice.setReservationInfoId(reservationDao.getReservationInfoId(reservationInfo));
			reservationDao.setReservationPrice(reservationInfoPrice);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReservedItem> getReservedItems(String userEmail, boolean isCanceled, boolean isExpired) {
		List<ReservedItem> reservedItems = new ArrayList<>();

		List<Reservation> reservations;

		if (isCanceled) {
			reservations = reservationDao.getCanceledReservations(userEmail);
		} else {
			if (isExpired) {
				reservations = reservationDao.getExpiredReservations(userEmail);
			} else {
				reservations = reservationDao.getAvailableReservations(userEmail);
			}
		}

		for (Reservation reservation : reservations) {
			ReservedItem reservedItem = new ReservedItem();

			reservedItem.setReservation(reservation);
			reservedItem.setTotalPrice(reservationDao.getTotalPrice(reservation.getId()));
			reservedItem.setDisplayInfo(detailProductDao.getDisplayInfo(reservation.getDisplayInfoId()));

			reservedItems.add(reservedItem);
		}

		return reservedItems;
	}

	@Override
	public void cancelReservation(Integer reservationInfoId) {
		reservationDao.cancelReservations(reservationInfoId);
	}
}