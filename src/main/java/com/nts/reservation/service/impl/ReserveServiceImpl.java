/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		ReserveDisplayInfo reserveDisplayInfo = reserveDisplayInfoDao.selectReviewDisplayInfoByDisplayInfoId(displayInfoId);
		
		//priceTypeName을 출력에 사용하는 형식으로 변환
		List<ReservePrice> reservePrice = reservePriceDao.selectReservePrice(displayInfoId);
		for (int i = 0; i < reservePrice.size(); i++) {
			ReservePrice targetPrice = reservePrice.get(i);
			String typeLabel = targetPrice.getPriceTypeName().getTypeLabel();
			targetPrice.setPriceTypeLabel(typeLabel);
		}
		
		//공연 정보 날짜를 오늘부터 1~5일후의 날짜로 무작위 생성.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, (int)((Math.random() * 5)) + 1);
		String reservationDate = dateFormat.format(calendar.getTime());
		
		return new ReserveResponse(reserveDisplayInfo, reservePrice, reservationDate);
	}

	@Override
	@Transactional(readOnly = false)
	public void registerReserve(ReserveRequest reserveRequest) {
		reserveDao.insertReservation(reserveRequest);
		int reservationInfoId = reserveRequest.getId();
		
		List<ReservePrice> reservePriceInfoList = reserveRequest.getReservePriceInfoList();

		for (int i = 0; i < reservePriceInfoList.size(); i++) {
			ReservePrice targetPriceInfo = reservePriceInfoList.get(i);
			targetPriceInfo.setReservationInfoId(reservationInfoId);

			if (reserveDao.insertReservationPrice(targetPriceInfo) < 1) {
				throw new RuntimeException("DB 갱신 오류");
			}
		}
	}

}
