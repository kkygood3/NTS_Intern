/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dto.myreservation.MyReservationResponse;
import com.nts.reservation.dto.myreservation.ReservationType;
import com.nts.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService {
	@Autowired
	MyReservationDao myReservationDao;

	@Override
	@Transactional(readOnly = true)
	public MyReservationResponse getMyReservationResponse(String email, ReservationType reservationType, Integer start,
		Integer pagingLimit) {

		int count = myReservationDao.selectMyReservationCount(email, reservationType.name());
		MyReservationResponse myReservationResponse = new MyReservationResponse(reservationType, count);
		if (count > start) {
			myReservationResponse.setReservationList(myReservationDao.selectMyReservation(email,
				reservationType.name(), start, pagingLimit));
		}
		return myReservationResponse;
	}

	@Override
	@Transactional
	public boolean cancelMyReservation(Integer reservationInfoId, String email) {
		return (myReservationDao.updateMyReservationCancel(reservationInfoId, email) > 0);
	}
}
