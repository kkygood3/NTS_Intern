package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.reservation.Reservation;
import com.nts.reservation.dto.reservation.ReservationInput;
import com.nts.reservation.service.ReservationService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Service
@Transactional(readOnly = false)
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;

	@Override
	public List<Reservation> getReservations(String email) {

		return null;
	}

	@Override
	public List<Reservation> postReservations(ReservationInput input) {
		System.out.println(reservationDao.insertReservationInfo(input));
		return null;
	}

}
