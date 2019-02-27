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
	public Map<String, Object> getReservationDisplayItemsByReservationEmailWithPaging(String Email, int startRow, int limit) {
		Map<String, Object> ReservationDisplayItemListMap = new HashMap<String, Object>();
		ReservationDisplayItemListMap.put("confirmed", reservationInfoDao.selectConfirmedReservationInfoByEmail(Email, startRow, limit));
		ReservationDisplayItemListMap.put("used", reservationInfoDao.selectUsedReservationInfoByEmail(Email, startRow, limit));
		ReservationDisplayItemListMap.put("cancel", reservationInfoDao.selectCancelReservationInfoByEmail(Email, startRow, limit));

		ReservationDisplayItemListMap.put("count", reservationInfoDao.selectReservationInfoCountByEmail(Email));

		return ReservationDisplayItemListMap;
	}

	@Override
	public List<ReservationDisplayItem> getReservationDisplayItemsByReservationEmailByTypeWithPaging(String Email, int startRow, int limit, String status) {
		if (Utils.isEmpty(status)) {
			return Collections.EMPTY_LIST;
		}
		if (ReservationStatus.CONFIRMED.equals(status)) {
			return reservationInfoDao.selectConfirmedReservationInfoByEmail(Email, startRow, limit);
		} else if (ReservationStatus.USED.equals(status)) {
			return reservationInfoDao.selectUsedReservationInfoByEmail(Email, startRow, limit);
		} else if (ReservationStatus.CANCEL.equals(status)) {
			return reservationInfoDao.selectCancelReservationInfoByEmail(Email, startRow, limit);
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail) {
		return reservationInfoDao.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, reservationEmail);
	}
}
