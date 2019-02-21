/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dto.myreservation.MyReservationInfo;
import com.nts.reservation.dto.myreservation.MyReservationResponse;
import com.nts.reservation.dto.myreservation.ReservationType;
import com.nts.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService {
	@Autowired
	MyReservationDao myReservationDao;
	@Autowired
	MyReservationDao myReservationMapper;
	
	@Override
	public MyReservationResponse getMyReservationResponse(String email, ReservationType reservationType, Integer start, Integer pagingLimit){
		
		Integer count = myReservationMapper.selectMyReservationCount(email, reservationType.name());
		MyReservationResponse myReservationResponse = new MyReservationResponse();
		if(count != null && count > start) {
			List<MyReservationInfo> myReservationList = myReservationMapper.selectMyReservation(email, reservationType.name(), start, pagingLimit);
			myReservationResponse.setReservationList(myReservationList);
			myReservationResponse.setReservationType(reservationType);
			myReservationResponse.setCount(count);
		}
		return myReservationResponse;
	}

	@Override
	public boolean cancelMyReservation(Integer reservationInfoId, String email) {
		if (myReservationDao.updateMyReservationCancelById(reservationInfoId, email) != null) {
			return true;
		}
		
		return false;
	}
}
