package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationInfoDao;
import com.nts.reservation.dao.ReservationInfoPriceDao;
import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationInfoPrice;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.UserReservationInput;
import com.nts.reservation.service.ReservationService;
import com.nts.reservation.service.validation.Validation;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Override
	@Transactional
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId) {
		return reservationInfoDao.selectReservationPageInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public ReservationInfo addReservation(UserReservationInput userReservationInput, Long displayInfoId) {
		if (!Validation.getInstance().validateEmail(userReservationInput.getEmail())
			|| !Validation.getInstance().validateTel(userReservationInput.getTel())
			|| !Validation.getInstance().validateName(userReservationInput.getName())) {
			return null;
		}
			
		ReservationInfo reservationInfo = new ReservationInfo(userReservationInput);
		reservationInfo.setDisplayInfoId(displayInfoId);
		reservationInfo.setReservationDate(new Date());
		reservationInfo.setCreateDate(new Date());
		Long reservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfo);
		reservationInfo.setId(reservationInfoId);
		for (ReservationInfoPrice reservationInfoPrice : userReservationInput.getPrice()) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
			reservationInfoPriceDao.insertReservationInfoPrice(reservationInfoPrice);
		}
		return reservationInfo;
	}
	
	@Override
	@Transactional
	public Map<String, List<ReservationDisplayItem>> getReservationDisplayItemsByReservationEmail (String reservationEmail, int start, int limit) {
		List<ReservationDisplayItem> reservationDisplayItemList = reservationInfoDao.selectReservationInfoByReservationEmail(reservationEmail, start, start + limit);

		Map<String, List<ReservationDisplayItem>> ReservationDisplayItemListMap = new HashMap<String, List<ReservationDisplayItem>>();
		List<ReservationDisplayItem> confirmed = new ArrayList<ReservationDisplayItem>();
		List<ReservationDisplayItem> used = new ArrayList<ReservationDisplayItem>();
		List<ReservationDisplayItem> cancel = new ArrayList<ReservationDisplayItem>();
		
		for (ReservationDisplayItem reservationDisplayItem : reservationDisplayItemList) {
			if (reservationDisplayItem.isCanceled()) {
				cancel.add(reservationDisplayItem);
			} else if (new Date().compareTo(reservationDisplayItem.getReservationDate()) < 0) {
				// TODO: 부등호 방향 변경
				used.add(reservationDisplayItem);
			} else {
				confirmed.add(reservationDisplayItem);
			}
		}
		ReservationDisplayItemListMap.put("confirmed", confirmed);
		ReservationDisplayItemListMap.put("used", used);
		ReservationDisplayItemListMap.put("cancel", cancel);
		return ReservationDisplayItemListMap;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int updateCancelFlagToFalseByReservationInfoId (long reservationInfoId, String reservationEmail) {
		return reservationInfoDao.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, reservationEmail);
	}
}
