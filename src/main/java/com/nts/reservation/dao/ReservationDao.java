/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationSqls.AVAILABLE;
import static com.nts.reservation.dao.sqls.ReservationSqls.EXPIRED;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO_PRICE;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_RESERVATIONS;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_RESERVATION_INFO_ID;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_TOTAL_PRICE;
import static com.nts.reservation.dao.sqls.ReservationSqls.UPDATE_RESERVATION_CANCEL_FLAG;

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

import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservationPrice;

/**
* @author  : 이승수
*/
@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public void insertReservation(Reservation reservationInfo) {
		jdbc.update(INSERT_RESERVATION_INFO, new BeanPropertySqlParameterSource(reservationInfo));
	}

	public int selectReservationInfoId(Reservation reservationInfo) {
		return jdbc.queryForObject(SELECT_RESERVATION_INFO_ID, new BeanPropertySqlParameterSource(reservationInfo),
			Integer.class);
	}

	public void insertReservationPrice(ReservationPrice reservationInfoPrice) {
		jdbc.update(INSERT_RESERVATION_INFO_PRICE, new BeanPropertySqlParameterSource(reservationInfoPrice));
	}

	public List<Reservation> selectAvailableReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 0);
		return jdbc.query(SELECT_RESERVATIONS + AVAILABLE, params, rowMapper);
	}

	public List<Reservation> selectExpiredReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 0);
		return jdbc.query(SELECT_RESERVATIONS + EXPIRED, params, rowMapper);
	}

	public List<Reservation> selectCanceledReservations(String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("userEmail", userEmail);
		params.put("cancelFlag", 1);
		return jdbc.query(SELECT_RESERVATIONS, params, rowMapper);
	}

	public int selectTotalPrice(int reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE, param, Integer.class);
	}

	public void updateReservationCancelFlag(Integer reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		jdbc.update(UPDATE_RESERVATION_CANCEL_FLAG, param);
	}
}