/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.myreservation.MyReservationDao;
import com.nts.reservation.dto.myreservation.MyReservationInfo;
import com.nts.reservation.dto.myreservation.MyReservationResponse;
import com.nts.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService {
	@Autowired
	MyReservationDao myReservationDao;

	@Override
	public MyReservationResponse getMyReservationInfoList(String email) throws ParseException {
		List<MyReservationInfo> myReservationInfoList = myReservationDao.selectMyReservationByEmail(email);

		List<MyReservationInfo> confirmList = new ArrayList<MyReservationInfo>();
		List<MyReservationInfo> completeList = new ArrayList<MyReservationInfo>();
		List<MyReservationInfo> cancelList = new ArrayList<MyReservationInfo>();

		Date today = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

		for (MyReservationInfo item : myReservationInfoList) {
			if (item.getCancelFlag() == 1) {
				cancelList.add(item);
			} else {
				if (today.before(format.parse(item.getReservationDate()))) {
					confirmList.add(item);
				} else {
					completeList.add(item);
				}
			}
		}
		return new MyReservationResponse(confirmList, completeList, cancelList);
	}

	@Override
	public boolean cancelMyReservation(Integer reservationInfoId) {
		boolean isComplete = false;
		Integer result = myReservationDao.updateMyReservationCancelById(reservationInfoId);
		if (result != null) {
			isComplete = true;
		}
		return isComplete;
	}
}
