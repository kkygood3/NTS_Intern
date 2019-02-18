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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	public List<ReservationInfo> getReservationInfo(String reservationEmail) {
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
	public int getTotalPrice(String reservationEmail, int productId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("productId", productId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Integer.class);
	}

}
