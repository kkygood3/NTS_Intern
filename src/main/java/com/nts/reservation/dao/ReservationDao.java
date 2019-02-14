/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationSqls.GET_RESERVATION_INFO_ID;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sqls.ReservationSqls.INSERT_RESERVATION_INFO_PRICE;

import javax.sql.DataSource;

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
}