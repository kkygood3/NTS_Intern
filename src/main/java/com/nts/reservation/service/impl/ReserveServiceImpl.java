/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.reserve.ReserveDisplayInfoDao;
import com.nts.reservation.dao.reserve.ReservePriceDao;
import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	ReservePriceDao reservePriceDao;
	@Autowired
	ReserveDisplayInfoDao reserveDisplayInfoDao;

	@Override
	public ReserveResponse getReserveResponse(int displayInfoId) {
		ReserveResponse reserveResponse = new ReserveResponse();
		reserveResponse.setReserveDisplayInfo(reserveDisplayInfoDao.selectReviewDisplayInfoByDisplayInfoId(displayInfoId));
		reserveResponse.setReservePrice(reservePriceDao.selectReservePrice(displayInfoId));
		return reserveResponse;	
	}

}
