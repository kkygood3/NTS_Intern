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
import com.nts.reservation.dto.reserve.ReserveDisplayInfo;
import com.nts.reservation.dto.reserve.ReservePrice;
import com.nts.reservation.dto.reserve.ReserveRequest;
import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.service.ReserveService;
import com.nts.reservation.utils.DateGenerator;

@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	ReservePriceDao reservePriceDao;
	@Autowired
	ReserveDisplayInfoDao reserveDisplayInfoDao;
	@Autowired
	ReserveDao reserveDao;

	@Override
	@Transactional(readOnly = true)
	public ReserveResponse getReserveResponse(int displayInfoId) {
		ReserveDisplayInfo reserveDisplayInfo = reserveDisplayInfoDao.selectReviewDisplayInfo(displayInfoId);

		//priceTypeName을 출력에 사용하는 형식으로 변환
		List<ReservePrice> reservePrice = reservePriceDao.selectReservePrice(displayInfoId);
		for (int i = 0; i < reservePrice.size(); i++) {
			ReservePrice targetPrice = reservePrice.get(i);
			String typeLabel = targetPrice.getPriceTypeName().getTypeLabel();
			targetPrice.setPriceTypeLabel(typeLabel);
		}
		
		return new ReserveResponse(reserveDisplayInfo, reservePrice, DateGenerator.getRandomDate());
	}

	@Override
	@Transactional
	public void registerReserve(ReserveRequest reserveRequest) {
		reserveDao.insertReservation(reserveRequest);
		reserveDao.insertReservationPrice(reserveRequest);
	}

}
