package com.nts.reservation.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationInfoMapper;
import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationInfoPrice;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.property.ReservationStatus;
import com.nts.reservation.service.ReservationService;
import com.nts.reservation.util.Utils;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoMapper reservationInfoDao;

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
			reservationInfoDao.insertReservationInfoPrice(reservationInfoPrice);
		}
		return reservationInfo;
	}

	@Override
	@Transactional
	public Map<String, Object> getReservationDisplayItemsByReservationEmailWithPaging(
		String reservationEmail, int start, int limit) {
		Map<String, Object> ReservationDisplayItemListMap = new HashMap<String, Object>();
		ReservationDisplayItemListMap.put("confirmed", reservationInfoDao.selectConfirmedReservationInfoByReservationEmail(reservationEmail, start, limit));
		ReservationDisplayItemListMap.put("used", reservationInfoDao.selectUsedReservationInfoByReservationEmail(reservationEmail, start, limit));
		ReservationDisplayItemListMap.put("cancel", reservationInfoDao.selectCancelReservationInfoByReservationEmail(reservationEmail, start, limit));
		
		ReservationDisplayItemListMap.put("count", reservationInfoDao.selectReservationInfoCountByReservationEmail(reservationEmail));
	
		return ReservationDisplayItemListMap;
	}
	
	@Override
	public List<ReservationDisplayItem> getReservationDisplayItemsByReservationEmailByTypeWithPaging(
		String reservationEmail, int start, int limit, String status) {
		if (Utils.isEmpty(status)) {
			return Collections.EMPTY_LIST;
		}
		if (status.equals(ReservationStatus.CONFIRMED.toString())) {
			return reservationInfoDao.selectConfirmedReservationInfoByReservationEmail(reservationEmail, start, limit);
		} else if (status.equals(ReservationStatus.USED.getReservationStatus())) {
			return reservationInfoDao.selectUsedReservationInfoByReservationEmail(reservationEmail, start, limit);
		} else if (status.equals(ReservationStatus.CANCEL.getReservationStatus())) {
			return reservationInfoDao.selectCancelReservationInfoByReservationEmail(reservationEmail, start, limit);
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail) {
		return reservationInfoDao.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, reservationEmail);
	}
}
