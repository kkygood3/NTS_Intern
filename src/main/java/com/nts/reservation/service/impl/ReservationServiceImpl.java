package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ReservationPageInfoDao;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationPageInfoDao reservationPageInfoDao;
	
	@Override
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId) {
		return reservationPageInfoDao.selectReservationPageInfoByDisplayInfoId(displayInfoId);
	}
}
