/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.common.exception.NotFoundDataException;
import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistory;
import com.nts.reservation.reservation.model.ReservationPrice;
import static com.nts.reservation.reservation.dao.querys.ReservationQuerys.*;

@Repository
public class ReservationDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<ReservationHistory> reservationHistoryMapper;

	@Autowired
	public ReservationDao(NamedParameterJdbcTemplate jdbcTemplate,
		RowMapper<ReservationHistory> reservationHistoryMapper) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.reservationHistoryMapper = reservationHistoryMapper;
	}

	/**
	 * 예매 기본 정보 저장
	 */
	public int insertReservation(Reservation reservation) {

		SqlParameterSource param = new MapSqlParameterSource()
			.addValues(new ObjectMapper().convertValue(reservation, Map.class));
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(INSERT_RESERVATION_INFO, param, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 예매한 티켓 정보 저장
	 */
	public int insertReservationInfoPrice(int reservationInfoId, ReservationPrice reservationPrice) {
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationInfoId)
			.addValues(new ObjectMapper().convertValue(reservationPrice, Map.class));

		return jdbcTemplate.update(INSERT_RESERVATION_INFO_PRICE, param);
	}

	/**
	 * 예매 기록 조회
	 */
	public List<ReservationHistory> selectReservationHistoryList(String reservationEmail) {
		Map<String, String> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return jdbcTemplate.query(SELECT_RESERVATION_HISTORY, param, reservationHistoryMapper);
	}

	/**
	 * 예매 상태 변경
	 */
	public int updateReservationCancelFlag(String reservationEmail, int reservationId, int statusCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("reservationEmail", reservationEmail);
		param.put("reservationId", reservationId);
		param.put("statusCode", statusCode);
		return jdbcTemplate.update(UPDATE_RESERVATION_CANCEL_FLAG_STRING, param);
	}

	/**
	 * 사용자가 예매했는지 조회 및 예매한 상품 이름 조회
	 */
	public String selectReservedProductDescription(String reservationEmail, int reservationId) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("reservationEmail", reservationEmail);
			param.put("reservationId", reservationId);

			return jdbcTemplate.queryForObject(SELECT_RESERVED_PRODUCT_DESCRIPTION, param, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundDataException("not found reserved data");
		}
	}

}
