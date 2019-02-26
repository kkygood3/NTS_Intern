/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO_PRICE;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_DISPLAY_INFO_ID_BY_RESERVATION_INFO_ID;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_FLAG_FOR_EXISTENCE_OF_RESERVATION;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_RESERVATIONS;
import static com.nts.reservation.dao.sqls.ReservationSqls.SELECT_RESERVATION_INFO_ID;
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

	public List<Reservation> selectReservations(String userEmail) {
		Map<String, String> param = Collections.singletonMap("userEmail", userEmail);
		return jdbc.query(SELECT_RESERVATIONS, param, rowMapper);
	}

	public void updateReservationCancelFlag(Integer reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		jdbc.update(UPDATE_RESERVATION_CANCEL_FLAG, param);
	}

	public boolean selectReservationInfoExistFlag(Integer reservationInfoId, String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		params.put("userEmail", userEmail);

		return jdbc.queryForObject(SELECT_FLAG_FOR_EXISTENCE_OF_RESERVATION, params, Boolean.class);
	}

	public Integer selectDisplayInfoId(Integer reservationInfoId) {
		Map<String, Integer> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_ID_BY_RESERVATION_INFO_ID, param, Integer.class);
	}
}