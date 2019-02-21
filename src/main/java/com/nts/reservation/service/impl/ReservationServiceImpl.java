package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationInfoMapper;
import com.nts.reservation.dao.ReservationInfoPriceDao;
import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationInfoPrice;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoMapper reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;

	@Override
	@Transactional
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId) {
		return reservationInfoDao.selectReservationPageInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public ReservationInfo addReservation(ReservationInfo reservationInfo, List<ReservationInfoPrice> priceInfo,  Long displayInfoId) {
		reservationInfo.setDisplayInfoId(displayInfoId);
		reservationInfo.setCreateDate(new Date());
		reservationInfoDao.insertReservationInfo(reservationInfo);
		Long reservationInfoId = reservationInfo.getId();

		for (ReservationInfoPrice reservationInfoPrice : priceInfo) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
			reservationInfoPriceDao.insertReservationInfoPrice(reservationInfoPrice);
		}
		return reservationInfo;
	}

	@Override
	@Transactional
	public Map<String, List<ReservationDisplayItem>> getReservationDisplayItemsByReservationEmail(
		String reservationEmail, int start, int limit) {
		List<ReservationDisplayItem> reservationDisplayItemList = reservationInfoDao
			.selectReservationInfoByReservationEmail(reservationEmail, start, limit);
		Map<String, List<ReservationDisplayItem>> ReservationDisplayItemListMap = new HashMap<String, List<ReservationDisplayItem>>();
		List<ReservationDisplayItem> confirmed = new ArrayList<ReservationDisplayItem>();
		List<ReservationDisplayItem> used = new ArrayList<ReservationDisplayItem>();
		List<ReservationDisplayItem> cancel = new ArrayList<ReservationDisplayItem>();
		for (ReservationDisplayItem reservationDisplayItem : reservationDisplayItemList) {
			if (reservationDisplayItem.isCanceled()) {
				cancel.add(reservationDisplayItem);
			} else if (expiredDate(reservationDisplayItem.getReservationDate())) {
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
	@Transactional(readOnly = false)
	public int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail) {
		return reservationInfoDao.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, reservationEmail);
	}

	private boolean expiredDate(Date expirationDate) {
		return new Date().compareTo(expirationDate) > 0;
	}
}
