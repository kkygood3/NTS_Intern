/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nts.reservation.dao.reserve.ReserveDao;
import com.nts.reservation.dao.reserve.ReserveDisplayInfoDao;
import com.nts.reservation.dao.reserve.ReservePriceDao;
import com.nts.reservation.dto.reserve.PriceInfo;
import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.property.CommonProperties;
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
	public boolean postReserve(String name, String telephone, String email, int displayInfoId, String priceInfo)
		throws JsonParseException, JsonMappingException, IOException {
		boolean isInsertComplete = true;

		isInsertComplete = (isInsertComplete && (name.length() > 0 && name.length() < 18));
		isInsertComplete = (isInsertComplete && (telephone.length() > 0 && Pattern.matches(CommonProperties.REG_TELEPHONE, telephone)));
		isInsertComplete = (isInsertComplete && (email.length() > 0 && email.length() < 51 && Pattern.matches(CommonProperties.REG_EMAIL, email)));
		isInsertComplete = (isInsertComplete && (displayInfoId > 0));

		if (isInsertComplete) {
			Integer insertRow;

			ObjectMapper objectMapper = new ObjectMapper();
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			List<PriceInfo> priceInfoList = objectMapper.readValue(priceInfo,
				typeFactory.constructCollectionType(List.class, PriceInfo.class));

			int reservationInfoId = reserveDao.insertReservation(name, telephone, email, displayInfoId);
			for (int i = 0; i < priceInfoList.size(); i++) {
				PriceInfo targetPriceInfo = priceInfoList.get(i);
				insertRow = reserveDao.insertReservationPrice(targetPriceInfo.getType(), targetPriceInfo.getCount(), displayInfoId, reservationInfoId);
				isInsertComplete = (isInsertComplete && (insertRow != null));
			}
		}
		return isInsertComplete;
	}

}
