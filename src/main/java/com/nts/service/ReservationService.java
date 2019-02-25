/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.service;

import java.util.List;

import com.nts.dto.reservationdto.Reservation;
import com.nts.dto.reservationdto.ReservationParam;
import com.nts.dto.reservationdto.ReservationPrice;
import com.nts.dto.reservationinfodto.ReservationInfo;

public interface ReservationService {

	List<ReservationInfo> getReservationInfosByReservationEmail(String reservationEmail);
	
	List<Reservation> getReservationsByReservationEmail(String reservationEmail);

	List<ReservationPrice> getReservationPricesByReservationInfoId(int reservationInfoId);

	int modifyCancelFlag(int reservationInfoId, int cancelFlag);
	
	int addReservation(ReservationParam reservationParam);
	
	int addReservationPrices(int key, List<ReservationPrice> prices);
}