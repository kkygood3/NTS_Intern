package com.nts.reservation.service;

import java.util.List;
import java.util.Map;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.UserReservationInput;

public interface ReservationService {
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId);

	public ReservationInfo addReservation(UserReservationInput userReservationInput, Long displayInfoId);

	public Map<String, List<ReservationDisplayItem>> getReservationDisplayItemsByReservationEmail(String reservationEmail, int start,	int limit);
}
