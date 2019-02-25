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
import com.nts.reservation.dto.reserve.ReservePrice;
import com.nts.reservation.dto.reserve.ReservePriceInfo;
import com.nts.reservation.dto.reserve.ReserveRequest;
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

		List<ReservePrice> reservePrice = reservePriceDao.selectReservePrice(displayInfoId);
		for (int i = 0; i < reservePrice.size(); i++) {
			ReservePrice targetPrice = reservePrice.get(i);
			targetPrice.setPriceTypeLabel(targetPrice.getPriceTypeName().getTypeLabel());
		}
		reserveResponse.setReservePrice(reservePrice);
		return reserveResponse;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean registerReserve(ReserveRequest reserveRequest) {
		reserveDao.insertReservation(reserveRequest);
		int reservationInfoId = reserveRequest.getId();
		List<ReservePriceInfo> reservePriceInfoList = reserveRequest.getReservePriceInfoList();

		for (int i = 0; i < reservePriceInfoList.size(); i++) {
			ReservePriceInfo targetPriceInfo = reservePriceInfoList.get(i);
			targetPriceInfo.setReservationInfoId(reservationInfoId);
			Integer resultRow = reserveDao.insertReservationPrice(targetPriceInfo);

			if (resultRow == null || resultRow == 0) {
				return false;
			}
		}
		return true;
	}

}
