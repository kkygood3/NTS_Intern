/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.sql.SQLException;
import java.util.List;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationRequestDto;
import com.nts.reservation.dto.request.ReservationUserCommentRequestDto;

/**
 * @author 육성렬
 */
public interface ReservationService {

	public void addReservation(ReservationRequestDto reservation);

	public List<ReservationInfoDto> getReservationList(String email);

	public Integer getReservationCount(String email);

	public Boolean cancelReservation(Long reservationId);

	ReservationInfoDto getReservation(Long reservationInfoId);

	boolean findFinishReservation(Long reservationInfoId, String email);

	void addReservationUserComment(ReservationUserCommentRequestDto requestDto, List<FileDto> files,
		Long reservationInfoId) throws SQLException;
}
