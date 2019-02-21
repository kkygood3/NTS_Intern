/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import javax.sql.DataSource;

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
@Repository
public class ReserveDao {
	private NamedParameterJdbcTemplate jdbc;

	public ReserveDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * api/reservation post로 새로운 예약 정보 등록
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public int insertReservation(String name, String telephone, String email, int displayInfoId,
		String reservationDate) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		params.addValue("telephone", telephone);
		params.addValue("email", email);
		params.addValue("displayInfoId", displayInfoId);
		params.addValue("reservationDate", reservationDate);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(ReserveSqls.INSERT_RESERVE, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}

	/**
	 * api/reservation post로 새로운 예약의 가격 정보 등록
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public Integer insertReservationPrice(ReservePriceType type, int count, int displayInfoId, int reservationInfoId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("type", type.name());
		params.addValue("count", count);
		params.addValue("displayInfoId", displayInfoId);
		params.addValue("reservationInfoId", reservationInfoId);
		return jdbc.update(ReserveSqls.INSERT_RESERVE_PRICE, params);
	}
}
