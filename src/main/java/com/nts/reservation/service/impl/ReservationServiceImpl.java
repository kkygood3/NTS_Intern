package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<List<ReservationDisplayItem>> getReservationDisplayItemsByReservationEmail (String reservationEmail, int start, int limit) {
		List<ReservationDisplayItem> reservationDisplayItemList = reservationInfoDao.selectReservationInfoByReservationEmail(reservationEmail, start, start + limit);

		List<List<ReservationDisplayItem>> ReservationDisplayItemLists = new ArrayList<List<ReservationDisplayItem>>();
		
		ReservationDisplayItemLists.add(new ArrayList<ReservationDisplayItem>());
		ReservationDisplayItemLists.add(new ArrayList<ReservationDisplayItem>());
		ReservationDisplayItemLists.add(new ArrayList<ReservationDisplayItem>());
		
		for (ReservationDisplayItem reservationDisplayItem : reservationDisplayItemList) {
			if (reservationDisplayItem.isCanceled()) {
				ReservationDisplayItemLists.get(2).add(reservationDisplayItem);
			} else if (new Date().compareTo(reservationDisplayItem.getReservationDate()) < 0) {
				ReservationDisplayItemLists.get(1).add(reservationDisplayItem);
			} else {
				ReservationDisplayItemLists.get(0).add(reservationDisplayItem);
			}
		}
		return ReservationDisplayItemLists;
	}
}
