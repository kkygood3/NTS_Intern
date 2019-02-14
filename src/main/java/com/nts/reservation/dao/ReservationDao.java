/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_RESERVATION_INFO_PRICE;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_COUNT_RESERVATION_BY_EMAIL;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_RESERVATION_INFO_BY_EMAIL;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationPriceRequestDto;
import com.nts.reservation.dto.request.ReservationRequestDto;

/**
 * @author 육성렬
 */
@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoDto> reservationRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationInfoDto.class);

	public ReservationDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Long insertReservation(ReservationRequestDto reservation) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_INFO, params, holder);
		return holder.getKey().longValue();
	}

	public int insertReservationPrice(long reservationInfoId, long productPriceId, int count) {
		Map<String, Object> map = new HashMap();
		map.put("reservationInfoId", reservationInfoId);
		map.put("productPriceId", productPriceId);
		map.put("count", count);
		return jdbc.update(INSERT_RESERVATION_INFO_PRICE, map);
	}

	public int[] insertReservationPrices(List<ReservationPriceRequestDto> priceList) {
		SqlParameterSource[] batchs = SqlParameterSourceUtils.createBatch(priceList.toArray());
		return jdbc.batchUpdate(INSERT_RESERVATION_INFO_PRICE, batchs);
	}

	public List<ReservationInfoDto> selectReservationsByEmail(String email) {
		Map<String, Object> map = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, map, reservationRowMapper);
	}

	public int countReservationsByEmail(String email) {
		Map<String, Object> map = Collections.singletonMap("email", email);
		return jdbc.queryForObject(SELECT_COUNT_RESERVATION_BY_EMAIL, map, Integer.class);
	}

	public Integer updateCancelReservation(Long reservationInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", reservationInfoId);
		int resultRows = jdbc.update(UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID, map);
		return resultRows;
	}
}
