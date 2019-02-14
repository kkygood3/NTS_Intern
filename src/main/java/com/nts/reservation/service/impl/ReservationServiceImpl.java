/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.Reservation;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;

	@Override
	public void setReservation(HttpSession session, Reservation reserveInfo) {
		session.setAttribute("userEmail", reserveInfo.getReservationEmail());

		reservationDao.setReservation(reserveInfo);
	}
}
