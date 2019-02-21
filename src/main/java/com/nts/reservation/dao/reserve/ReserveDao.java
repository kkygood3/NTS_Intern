/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.ReserveSqls;
import com.nts.reservation.dto.reserve.ReservePriceType;

/**
 * reservation 등록에 사용
 */
public interface ReserveDao {
	/**
	 * api/reservation post로 새로운 예약 정보 등록
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public int insertReservation(
		@Param("name") String name,
		@Param("telephone") String telephone,
		@Param("email") String email,
		@Param("displayInfoId") int displayInfoId,
		@Param("reservationDate") String reservationDate);

	/**
	 * api/reservation post로 새로운 예약의 가격 정보 등록
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public Integer insertReservationPrice(
		@Param("type") ReservePriceType type,
		@Param("count") int count,
		@Param("displayInfoId") int displayInfoId,
		@Param("reservationInfoId") int reservationInfoId);
}
