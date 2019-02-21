/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dao;

import java.util.List;

import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationPrice;
import com.nts.reservation.reservation.dto.ReservationResponseData;

public interface ReservationDao {
	List<ReservationPrice> getReservationPrices(int productId);

	List<ReservationInfo> getReservationInfos(String reservationEmail);

	ReservationResponseData getReservationResponseData(ReservationParam reservationParam);

	int getReservationSize(String reservationEmail);

	int getTotalPrice(String reservationEmail, int productId);

	int insertReservation(String name, String telephone, String email, int displayInfoId);

	Integer insertReservationPrice(int productPriceId, int reservationInfoId, int count);
}
