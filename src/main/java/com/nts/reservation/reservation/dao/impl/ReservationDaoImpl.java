/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao.impl;

import static com.nts.reservation.reservation.dao.query.ReservationDaoQuerys.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.reservation.dto.ReservationDisplayInfo;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class ReservationDaoImpl {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationPrice.class);
	private RowMapper<ReservationDisplayInfo> reservationDisplayInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationDisplayInfo.class);
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationInfo.class);

	public ReservationDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertReservation(String name, String telephone, String email, int displayInfoId,
		String reservationDate) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		params.addValue("telephone", telephone);
		params.addValue("email", email);
		params.addValue("displayInfoId", displayInfoId);
		params.addValue("reservationDate", reservationDate);
		//		KeyHolder keyHolder = new GeneratedKeyHolder();
		//		jdbc.update(INSERT_RESERVATION, params, keyHolder, new String[] {"ID"});
		//		return keyHolder.getKey().intValue();
		return jdbc.update(INSERT_RESERVATION, params);
	}

	//	public int insertReservationPrice(String type, int count, int displayInfoId, int reservationInfoId) {
	//		MapSqlParameterSource params = new MapSqlParameterSource();
	//		params.addValue("type", type);
	//		params.addValue("count", count);
	//		params.addValue("displayInfoId", displayInfoId);
	//		params.addValue("reservationInfoId", reservationInfoId);
	//		return jdbc.update(INSERT_RESERVATION_PRICE, params);
	//	}

	public List<ReservationPrice> selectReservationPrice(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_RESERVATION_PRICE, param, reservationPriceRowMapper);
	}

	public ReservationDisplayInfo selectReservationDisplayInfo(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_RESERVATION_DISPLAY_INFO, param, reservationDisplayInfoRowMapper);
	}

	public List<ReservationInfo> selectReservationInfo(String reservationEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATION_INFO, params, reservationInfoRowMapper);
	}

	public int selectTotalPrice(String reservationEmail, int displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Integer.class);
	}
}