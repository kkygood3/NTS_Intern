/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationRequestDto;

/**
 * @author 육성렬
 */
public interface ReservationService {

	public void addReservation(ReservationRequestDto reservation);

	public List<ReservationInfoDto> getReservationList(String email);

	public Integer getReservationCount(String email);

	public Boolean cancelReservation(Long reservationId);
}
