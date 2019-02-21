/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	private ReservationDao reservationDao;
	@Autowired
	private DetailProductDao detailProductDao;

	@Override
	public void makeReservation(Reservation reservationInfo) {
		reservationDao.insertReservation(reservationInfo);

		List<ReservationPrice> reservationInfoPrices = reservationInfo.getPrices();
		int reservationInfoId = reservationDao.selectReservationInfoId(reservationInfo);
		for (ReservationPrice reservationInfoPrice : reservationInfoPrices) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(reservationInfoPrice);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReservedItem> getReservedItems(String userEmail) {
		List<ReservedItem> reservedItems = new ArrayList<>();
		List<Reservation> reservations = reservationDao.selectReservations(userEmail);

		for (Reservation reservation : reservations) {
			ReservedItem reservedItem = new ReservedItem();

			reservedItem.setReservation(reservation);
			reservedItem.setDisplayInfo(detailProductDao.selectDisplayInfo(reservation.getDisplayInfoId()));

			reservedItems.add(reservedItem);
		}

		return reservedItems;
	}

	@Override
	public void cancelReservation(Integer reservationInfoId) {
		reservationDao.updateReservationCancelFlag(reservationInfoId);
	}

	@Override
	public boolean seletReservationInfoExistFlag(Integer displayInfoId, String userEmail) {
		return reservationDao.seletReservationInfoExistFlag(displayInfoId, userEmail);
	}
}