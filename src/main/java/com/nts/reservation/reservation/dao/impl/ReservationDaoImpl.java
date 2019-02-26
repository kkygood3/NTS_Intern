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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.dto.ReservationDisplayInfo;
import com.nts.reservation.reservation.dto.ReservationInfo;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class ReservationDaoImpl implements ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationDisplayInfo> reservationDisplayInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationDisplayInfo.class);
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationInfo.class);

	public ReservationDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int insertReservationInfo(String reservationName, String reservationTel, String reservationEmail,
		int displayInfoId,
		String reservationDate) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationName", reservationName);
		params.addValue("reservationTel", reservationTel);
		params.addValue("reservationEmail", reservationEmail);
		params.addValue("displayInfoId", displayInfoId);
		params.addValue("reservationDate", reservationDate);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_INFO, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public ReservationDisplayInfo selectReservationDisplayInfo(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_RESERVATION_DISPLAY_INFO, param, reservationDisplayInfoRowMapper);
	}

	@Override
	public int cancelReservation(int reservationInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);
		return jdbc.update(CANCEL_RESERVATION, param);
	}

	@Override
	public List<ReservationInfo> selectConfirmReservationInfo(String reservationEmail, int start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_CONFIRM_RESERVATION_INFO, params, reservationInfoRowMapper);
	}

	@Override
	public List<ReservationInfo> selectCompleteReservationInfo(String reservationEmail, int start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_COMPLETE_RESERVATION_INFO, params, reservationInfoRowMapper);
	}

	@Override
	public List<ReservationInfo> selectCancelReservationInfo(String reservationEmail, int start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_CANCEL_RESERVATION_INFO, params, reservationInfoRowMapper);
	}

	@Override
	public int selectConfirmReservationInfoCount(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.queryForObject(SELECT_CONFIRM_RESERVATION_INFO_COUNT, params, Integer.class);
	}

	@Override
	public int selectCompleteReservationInfoCount(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.queryForObject(SELECT_COMPLETE_RESERVATION_INFO_COUNT, params, Integer.class);
	}

	@Override
	public int selectCancelReservationInfoCount(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.queryForObject(SELECT_CANCEL_RESERVATION_INFO_COUNT, params, Integer.class);
	}
}