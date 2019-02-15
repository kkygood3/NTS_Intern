package com.nts.reservation.service;

import com.nts.reservation.dto.ReservationPageInfo;

public interface ReservationService {
	public ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId);
}
