/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao;

import java.util.List;

import com.nts.reservation.reservation.dto.ReservationPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ReservationPriceDao {
	int insertReservationPrice(int reservationInfoId, String typeName, int count, int displayInfoId);

	List<ReservationPrice> selectReservationPrice(int displayInfoId);
}
