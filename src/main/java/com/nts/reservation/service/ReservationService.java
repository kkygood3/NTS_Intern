/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservedItem;

/**
* @author  : 이승수
*/
public interface ReservationService {
	void makeReservation(HttpSession session, Reservation reserveInfo);

	List<ReservedItem> getReservedItems(String userEmail, boolean isCanceled, boolean isExpired);

	void cancelReservation(Integer reservationInfoId);
}