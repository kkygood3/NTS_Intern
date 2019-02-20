package com.nts.reservation.reserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;

@Service
public class RervationServiceImpl {

	@Autowired
	ReservationDao reservationDao;
	
	// TODO interface 구현

	public ReservationResponse insertRerservationParam(ReservationParam reservationParam) {

		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);

		reservationParam.getPrice().forEach(price -> {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertProductPrice(price);
		});

		return ReservationResponse.builder().build();
	}
	
	// TODO void가 맞을지?
	public void insertReservationParam(ReservationParam reservationParam) {
		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);

		reservationParam.getPrice().forEach(price -> {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertProductPrice(price);
		});
		
	}

}
