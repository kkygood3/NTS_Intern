package com.nts.reservation.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationInfoDao;
import com.nts.reservation.dao.ReservationInfoPriceDao;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationInfoPrice;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.UserReservationInput;
import com.nts.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Override
	@Transactional
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId) {
		return reservationInfoDao.selectReservationPageInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public ReservationInfo addReservation(UserReservationInput userReservationInput, Long displayInfoId) {
		ReservationInfo reservationInfo = new ReservationInfo(userReservationInput);
		reservationInfo.setDisplayInfoId(displayInfoId);
		reservationInfo.setReservationDate(new Date());
		reservationInfo.setCreateDate(new Date());
		Long reservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfo);
		reservationInfo.setId(reservationInfoId);
		for (ReservationInfoPrice reservationInfoPrice : userReservationInput.getPrice()) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
			reservationInfoPriceDao.insertReservationInfoPrice(reservationInfoPrice);
		}
		return reservationInfo;
	}
}
