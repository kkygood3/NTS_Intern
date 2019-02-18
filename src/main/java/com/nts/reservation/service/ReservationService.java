/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservedItem;

/**
* @author  : 이승수
*/
public interface ReservationService {
	public void makeReservation(Reservation reservationInfo);

	public List<ReservedItem> getReservedItems(String userEmail, boolean isCanceled, boolean isExpired);

	public void cancelReservation(Integer reservationInfoId);
}