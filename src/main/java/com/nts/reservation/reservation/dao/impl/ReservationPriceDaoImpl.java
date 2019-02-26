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

import com.nts.reservation.reservation.dao.ReservationPriceDao;
import com.nts.reservation.reservation.dto.ReservationPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class ReservationPriceDaoImpl implements ReservationPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationPrice.class);

	public ReservationPriceDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int insertReservationPrice(int reservationInfoId, String typeName, int count, int displayInfoId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationInfoId", reservationInfoId);
		params.addValue("type", typeName);
		params.addValue("count", count);
		params.addValue("displayInfoId", displayInfoId);
		return jdbc.update(INSERT_RESERVATION_PRICE, params);
	}

	@Override
	public List<ReservationPrice> selectReservationPrice(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_RESERVATION_PRICE, param, reservationPriceRowMapper);
	}

}
