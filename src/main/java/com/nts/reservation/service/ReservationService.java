/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.io.IOException;
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

	public void cancelReservation(Long reservationId);

	public ReservationInfoDto getReservation(Long reservationInfoId);

	public boolean findFinishReservation(Long reservationInfoId, String email);

	public void addReservationUserComment(ReservationUserCommentRequestDto requestDto, Long reservationInfoId)
			throws IOException;

	public FileDto getFileByCommentImageId(Long commentImageId);
}
