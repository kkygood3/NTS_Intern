/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.common.ReservationValidatior;
import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dto.myreservation.MyReservationInfo;
import com.nts.reservation.property.CommonProperties;
import com.nts.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService {
	@Autowired
	MyReservationDao myReservationDao;

	@Override
	public List<MyReservationInfo> getMyReservationInfoList(String email) {
		if(ReservationValidatior.validateEmail(email)) {
			List<MyReservationInfo> myReservationInfoList = myReservationDao.selectMyReservationByEmail(email);
			SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy.MM.dd(E)");
			Calendar calendar = Calendar.getInstance();
			
			int listSize = myReservationInfoList.size();
			for(int i = 0 ; i < listSize; i++) {
				int timeVariance = (int)((Math.random() * 11))-5;
				calendar.add(Calendar.DAY_OF_MONTH, timeVariance);
				myReservationInfoList.get(i).setDisplayDate(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, timeVariance * -1);
			}
			return myReservationInfoList;
		} else {
			return Collections.emptyList();
		}
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
