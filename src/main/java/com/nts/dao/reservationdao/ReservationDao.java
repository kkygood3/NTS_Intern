/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao.reservationdao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.dto.reservationdto.Reservation;
import com.nts.dto.reservationdto.ReservationPrice;

import static com.nts.dao.reservationdao.ReservationDaoSqls.*;

@Repository
public class ReservationDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);
	
	public List<Reservation> selectReservationsByReservationEmail(String reservationEmail) {
		Map<String, ?> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATIONS_BY_RESERVATION_EMAIL, param, reservationRowMapper);
	}
	
	public List<ReservationPrice> selectReservationPricesByReservationInfoId(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.query(SELECT_RESERVATION_PRICES_BY_RESERVATION_INFO_ID, param, reservationPriceRowMapper);
	}
	
	public int selectTotalPriceByReservationInfoId(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE_BY_RESERVATION_INFO_ID, param, Integer.class);
	}
	
	public int updateCancelFlagByReservationInfoId(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.update(UPDATE_CANCEL_FLAG_BY_RESERVATION_INFO_ID, param);
	}

}
