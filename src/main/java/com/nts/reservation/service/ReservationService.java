package com.nts.reservation.service;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.UserReservationInput;

public interface ReservationService {
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId);

	public ReservationInfo addReservation(UserReservationInput userReservationInput, Long displayInfoId);
}
