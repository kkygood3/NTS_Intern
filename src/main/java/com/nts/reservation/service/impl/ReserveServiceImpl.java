/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.reserve.ReserveDao;
import com.nts.reservation.dao.reserve.ReserveDisplayInfoDao;
import com.nts.reservation.dao.reserve.ReservePriceDao;
import com.nts.reservation.dto.reserve.PriceInfo;
import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	ReservePriceDao reservePriceDao;
	@Autowired
	ReserveDisplayInfoDao reserveDisplayInfoDao;
	@Autowired
	ReserveDao reserveDao;
	
	@Override
	public ReserveResponse getReserveResponse(int displayInfoId) {
		ReserveResponse reserveResponse = new ReserveResponse();
		reserveResponse.setReserveDisplayInfo(reserveDisplayInfoDao.selectReviewDisplayInfoByDisplayInfoId(displayInfoId));
		reserveResponse.setReservePrice(reservePriceDao.selectReservePrice(displayInfoId));
		return reserveResponse;	
	}

	@Transactional
	@Override
	public void postReserve(String name, String telephone, String email, int displayInfoId, List<PriceInfo> priceInfoList) {
		int reservationInfoId = reserveDao.insertReservation(name, telephone, email, displayInfoId);
		for(int i = 0 ; i < priceInfoList.size(); i++) {
			PriceInfo targetPriceInfo = priceInfoList.get(i);
			reserveDao.insertReservationPrice(targetPriceInfo.getType(), targetPriceInfo.getCount(), displayInfoId, reservationInfoId);	
		}
	}

}
