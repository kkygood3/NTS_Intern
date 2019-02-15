/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationSqls.AVAILABLE;
import static com.nts.reservation.dao.sqls.ReservationSqls.CANCEL_RESERVATION;
import static com.nts.reservation.dao.sqls.ReservationSqls.EXPIRED;
import static com.nts.reservation.dao.sqls.ReservationSqls.GET_RESERVATIONS;
import static com.nts.reservation.dao.sqls.ReservationSqls.GET_RESERVATION_INFO_ID;
import static com.nts.reservation.dao.sqls.ReservationSqls.GET_TOTAL_PRICE;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO_PRICE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservationPrice;

/**
* @author  : 이승수
*/
@Repository
@Transactional
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public void setReservation(Reservation reservationInfo) {
		jdbc.update(INSERT_RESERVATION_INFO, new BeanPropertySqlParameterSource(reservationInfo));
	}

	@Transactional(readOnly = true)
	public int getReservationInfoId(Reservation reservationInfo) {
		return jdbc.queryForObject(GET_RESERVATION_INFO_ID, new BeanPropertySqlParameterSource(reservationInfo),
			Integer.class);
	}

	public void setReservationPrice(ReservationPrice reservationInfoPrice) {
		jdbc.update(INSERT_RESERVATION_INFO_PRICE, new BeanPropertySqlParameterSource(reservationInfoPrice));
	}

	@Transactional(readOnly = true)
	public List<Reservation> getAvailableReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 0);
		return jdbc.query(GET_RESERVATIONS + AVAILABLE, params, rowMapper);
	}

	@Transactional(readOnly = true)
	public List<Reservation> getExpiredReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 0);
		return jdbc.query(GET_RESERVATIONS + EXPIRED, params, rowMapper);
	}

	@Transactional(readOnly = true)
	public List<Reservation> getCanceledReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 1);
		return jdbc.query(GET_RESERVATIONS, params, rowMapper);
	}

	public int getTotalPrice(int reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(GET_TOTAL_PRICE, param, Integer.class);
	}

	public void cancelReservations(Integer reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		jdbc.update(CANCEL_RESERVATION, param);
	}
}