/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao;

import static com.nts.reservation.reservation.dao.querys.ReservationQuerys.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistory;
import com.nts.reservation.reservation.model.ReservationPrice;
import com.nts.reservation.reservation.model.ReservationStatus;

@Repository
public class ReservationDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<ReservationHistory> reservationHistoryMapper;

	public int insertReservation(Reservation reservation) {

		SqlParameterSource param = new MapSqlParameterSource()
			.addValues(new ObjectMapper().convertValue(reservation, Map.class));
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(INSERT_RESERVATION_INFO, param, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void insertReservationInfoPrice(int reservationInfoId, ReservationPrice reservationPrice) {
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationInfoId)
			.addValues(new ObjectMapper().convertValue(reservationPrice, Map.class));

		jdbcTemplate.update(INSERT_RESERVATION_INFO_PRICE, param);
	}

	public List<ReservationHistory> selectReservationHistoryList(String reservationEmail) {
		Map<String, String> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return jdbcTemplate.query(SELECT_RESERVATION_HISTORY, param, reservationHistoryMapper);
	}

	public void updateReservationCancelFlag(int reservationId, int statusCode) {
		Map<String, Integer> param = new HashMap<>();
		param.put("reservationId", reservationId);
		param.put("statusCode", statusCode);
		jdbcTemplate.update(UPDATE_RESERVATION_CANCEL_FLAG_STRING, param);
	}

}
