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
	/**
	 * 나의 예약목록을 조회
	 * @param email
	 * @param type
	 * @param start
	 * @param pagingLimit
	 */
	List<MyReservationInfo> selectMyReservation(
		@Param("email") String email,
		@Param("type") String type,
		@Param("start") int start,
		@Param("pagingLimit") int pagingLimit);

	/**
	 * 나의 예약목록 조회시 페이징 처리에 사용할 Count 조회
	 * @param email
	 * @param type
	 */
	Integer selectMyReservationCount(
		@Param("email") String email,
		@Param("type") String type);

	/**
	 * 예약 취소 수행 
	 * @param reservationInfoId
	 * @param email
	 */
	Integer updateMyReservationCancel(
		@Param("reservationInfoId") Integer reservationInfoId,
		@Param("email") String email);
}
