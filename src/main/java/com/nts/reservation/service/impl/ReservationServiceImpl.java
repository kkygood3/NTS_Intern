package com.nts.reservation.service.impl;

import java.util.Collections;
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
	public ReservationInfo addReservation(ReservationInfo reservationInfo, List<ReservationInfoPrice> reservationInfoPriceList, Long displayInfoId) {
		reservationInfo.setDisplayInfoId(displayInfoId);
		reservationInfoDao.insertReservationInfo(reservationInfo);

		Map<String, Object> reservationInfoPrice = new HashMap<String, Object>();
		reservationInfoPrice.put("reservationInfoId", reservationInfo.getId());
		reservationInfoPrice.put("reservationInfoPriceList", reservationInfoPriceList);

		reservationInfoDao.insertReservationInfoPrice(reservationInfoPrice);
		return reservationInfo;
	}

	@Override
	@Transactional
	public Map<String, Object> getReservationDisplayItemsByReservationEmailWithPaging(String Email, int start,int limit) {
		Map<String, Object> ReservationDisplayItemListMap = new HashMap<String, Object>();
		ReservationDisplayItemListMap.put("confirmed",
				reservationInfoDao.selectConfirmedReservationInfoByEmail(Email, start, limit));
		ReservationDisplayItemListMap.put("used",
				reservationInfoDao.selectUsedReservationInfoByEmail(Email, start, limit));
		ReservationDisplayItemListMap.put("cancel",
				reservationInfoDao.selectCancelReservationInfoByEmail(Email, start, limit));

		ReservationDisplayItemListMap.put("count", reservationInfoDao.selectReservationInfoCountByEmail(Email));

		return ReservationDisplayItemListMap;
	}

	@Override
	public List<ReservationDisplayItem> getReservationDisplayItemsByReservationEmailByTypeWithPaging(String Email,
			int start, int limit, String status) {
		if (Utils.isEmpty(status)) {
			return Collections.EMPTY_LIST;
		}
		if (ReservationStatus.CONFIRMED.equals(status)) {
			return reservationInfoDao.selectConfirmedReservationInfoByEmail(Email, start, limit);
		} else if (ReservationStatus.USED.equals(status)) {
			return reservationInfoDao.selectUsedReservationInfoByEmail(Email, start, limit);
		} else if (ReservationStatus.CANCEL.equals(status)) {
			return reservationInfoDao.selectCancelReservationInfoByEmail(Email, start, limit);
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail) {
		return reservationInfoDao.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, reservationEmail);
	}
}
