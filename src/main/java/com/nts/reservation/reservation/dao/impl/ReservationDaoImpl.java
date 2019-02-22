/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dao.impl;

import static com.nts.reservation.reservation.dao.queries.ReservationQueries.*;

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
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationPrice;
import com.nts.reservation.reservation.dto.ReservationResponseData;

@Repository
public class ReservationDaoImpl implements ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationPrice> rowMapperReservationPrice = BeanPropertyRowMapper
		.newInstance(ReservationPrice.class);
	private RowMapper<ReservationInfo> rowMapperReservationInfo = BeanPropertyRowMapper
		.newInstance(ReservationInfo.class);

	public ReservationDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<ReservationPrice> getReservationPrices(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		return jdbc.query(SELECT_RESERVATION_PRICES, params, rowMapperReservationPrice);
	}

	@Override
	public List<ReservationInfo> getReservationInfos(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATION_INFO, params, rowMapperReservationInfo);
	}

	@Override
	public ReservationResponseData getReservationResponseData(ReservationParam reservationParam) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationParam.getReservationEmail());
		return jdbc.queryForObject(SELECT_RESERVATION_RESPONSE, params, ReservationResponseData.class);
	}

	@Override
	public int getReservationSize(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.queryForObject(SELECT_RESERVATION_SIZE, params, Integer.class);
	}

	@Override
	public int getTotalPrice(String reservationEmail, int productId, int reservationInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("productId", productId);
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Integer.class);
	}

	@Override
	public int insertReservation(String name, String telephone, String email, int displayInfoId, String reservationDate) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		params.addValue("telephone", telephone);
		params.addValue("email", email);
		params.addValue("displayInfoId", displayInfoId);
		params.addValue("reservationDate", reservationDate);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVE, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public Integer insertReservationPrice(int productPriceId, int reservationInfoId, int count) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productPriceId", productPriceId);
		params.addValue("reservationInfoId", reservationInfoId);
		params.addValue("count", count);
		return jdbc.update(INSERT_RESERVE_PRICE, params);
	}

	@Override
	public int updateReservation(int reservationInfoId, String reservationEmail) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationInfoId", reservationInfoId);
		params.addValue("reservationEmail", reservationEmail);
		return jdbc.update(UPDATE_RESERVE_INFO, params);
	}

}
