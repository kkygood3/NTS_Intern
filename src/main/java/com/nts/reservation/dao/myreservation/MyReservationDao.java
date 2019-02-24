/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.myreservation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.myreservation.MyReservationInfo;

/**
 * myreservation 페이지에서 이용
 */
public interface MyReservationDao {
	public List<MyReservationInfo> selectMyReservation(
		@Param("email") String email,
		@Param("type") String type,
		@Param("start") int start,
		@Param("pagingLimit") int pagingLimit);

	public Integer selectMyReservationCount(
		@Param("email") String email,
		@Param("type") String type);

	public Integer updateMyReservationCancel(
		@Param("reservationInfoId") Integer reservationInfoId,
		@Param("email") String email);
}
