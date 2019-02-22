package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationDaoSqls.INSERT_RESERVATION_PRICE;
import static com.nts.reservation.dao.sqls.ReservationDaoSqls.INSERT_RESERVATION_RESERVATION_INFO;
import static com.nts.reservation.dao.sqls.ReservationDaoSqls.SELECT_RESERVATIONS;
import static com.nts.reservation.dao.sqls.ReservationDaoSqls.SELECT_RESERVATIONS_COUNT;
import static com.nts.reservation.dao.sqls.ReservationDaoSqls.SELECT_RESERVATION_BY_EMAIL_AND_RSV_ID;
import static com.nts.reservation.dao.sqls.ReservationDaoSqls.UPDATE_RESERVATION_CANCEL_FLAG;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.dto.reservation.ReservationParam;
import com.nts.reservation.dto.reservation.ReservationPrice;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@Repository
public class ReservationDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public long insertReservationInfo(ReservationParam input) {
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", input.getProductId());
		params.addValue("displayInfoId", input.getDisplayInfoId());
		params.addValue("reservationName", input.getReservationName());
		params.addValue("reservationTel", input.getReservationTelephone());
		params.addValue("reservationEmail", input.getReservationEmail());
		params.addValue("reservationYearMonthDay", input.getReservationYearMonthDay());
		jdbc.update(INSERT_RESERVATION_RESERVATION_INFO, params, holder, new String[] {"id"});
		return holder.getKey().longValue();
	}

	public Long insertReservationPrice(ReservationPrice price) {
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("count", price.getCount());
		params.addValue("productPriceId", price.getProductPriceId());
		params.addValue("reservationInfoId", price.getReservationInfoId());
		jdbc.update(INSERT_RESERVATION_PRICE, params, holder, new String[] {"id"});
		return holder.getKey().longValue();
	}

	public List<ReservationInfo> selectResevations(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		return jdbc.query(SELECT_RESERVATIONS, params, BeanPropertyRowMapper.newInstance(ReservationInfo.class));
	}

	public Long selectResevationsCount(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		return jdbc.queryForObject(SELECT_RESERVATIONS_COUNT, params, BeanPropertyRowMapper.newInstance(Long.class));
	}

	public int updateResevationCancelFlag(Long reservationId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationId", reservationId);
		return jdbc.update(UPDATE_RESERVATION_CANCEL_FLAG, params);
	}

	public ReservationInfo selectReservationByEmailAndRsvId(String email, Long reservationId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		params.addValue("reservationId", reservationId);
		return jdbc.queryForObject(SELECT_RESERVATION_BY_EMAIL_AND_RSV_ID, params,
			BeanPropertyRowMapper.newInstance(ReservationInfo.class));
	}
}
