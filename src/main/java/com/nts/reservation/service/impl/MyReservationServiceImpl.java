/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dto.myreservation.MyReservationInfo;
import com.nts.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService {
	@Autowired
	MyReservationDao myReservationDao;

	@Override
	public List<MyReservationInfo> getMyReservationInfoList(String email) {
		return myReservationDao.selectMyReservationByEmail(email);
	}

	@Override
	public boolean cancelMyReservation(Integer reservationInfoId) {
		boolean isComplete = false; 
		Integer result = myReservationDao.updateMyReservationCancelById(reservationInfoId);
		if(result != null) {
			isComplete = true;
		}
		return isComplete;
	}
}
