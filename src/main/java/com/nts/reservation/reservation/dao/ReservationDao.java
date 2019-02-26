/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao;

import java.util.List;

import com.nts.reservation.reservation.dto.ReservationDisplayInfo;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ReservationDao {
	int insertReservationInfo(String reservationName, String reservationTel, String reservationEmail,
		int displayInfoId,
		String reservationDate);

	int insertReservationPrice(int reservationInfoId, String typeName, int count, int displayInfoId);

	List<ReservationPrice> selectReservationPrice(int displayInfoId);

	ReservationDisplayInfo selectReservationDisplayInfo(int displayInfoId);

	int cancelReservation(int reservationInfoId);

	List<ReservationInfo> selectConfirmReservationInfo(String reservationEmail, int start, int limit);

	List<ReservationInfo> selectCompleteReservationInfo(String reservationEmail, int start, int limit);

	List<ReservationInfo> selectCancelReservationInfo(String reservationEmail, int start, int limit);

	int selectConfirmReservationInfoCount(String reservationEmail);

	int selectCompleteReservationInfoCount(String reservationEmail);

	int selectCancelReservationInfoCount(String reservationEmail);
}
