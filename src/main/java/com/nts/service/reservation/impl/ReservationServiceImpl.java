/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.reservation.ReservationRepository;
import com.nts.dto.reservation.ReservationData;
import com.nts.dto.reservation.ReservationInfo;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.dto.reservation.ReservationPrice;
import com.nts.exception.DisplayInfoNullException;
import com.nts.service.displayInfo.DisplayInfoService;
import com.nts.service.reservation.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private DisplayInfoService displayInfoService;

	@Override
	public ReservationInfos getReservationInfoByReservationEmail(String reservationEmail) throws DisplayInfoNullException{

		List<ReservationInfo> reservationInfoList = reservationRepository.selectReservationInfoByReservationEmail(reservationEmail);
		
		
		for(ReservationInfo reservation : reservationInfoList) {
			reservation.setDisplayInfo(displayInfoService.getDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
		}
		
		ReservationInfos reservationInfos = new ReservationInfos();
		
		reservationInfos.setReservations(reservationInfoList);
		reservationInfos.setSize(reservationInfoList.size());
		
		return reservationInfos;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {SQLException.class})
	public int addReservation(ReservationData reservationData) {
		long reservationInfoId = reservationRepository.insertReservation(reservationData);
		
		for(ReservationPrice reservationPrice : reservationData.getPrices()) {
			reservationPrice.setReservationInfoId(reservationInfoId);
			
			reservationRepository.insertReservationPrice(reservationPrice);
		}
		return 1;
	}

	@Override
	public int modifyReservationToCancelByReservationId(long reservationId) {
		
		return reservationRepository.updateReservationToCancelByReservationId(reservationId);
	}
}
