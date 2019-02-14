/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

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
		String sql = "Insert Into "
			+ "reservation_info"
			+ "(product_id"
			+ ", display_info_id"
			+ ", reservation_name"
			+ ", reservation_tel"
			+ ", reservation_email"
			+ ", reservation_date"
			+ ", create_date"
			+ ", modify_date) "
			+ "VALUES"
			+ "(:productId"
			+ ", :displayInfoId"
			+ ", :reservationName"
			+ ", :reservationTelephone"
			+ ", :reservationEmail"
			+ ", :reservationYearMonthDay"
			+ ", NOW()"
			+ ", NOW())";

		jdbc.update(sql, new BeanPropertySqlParameterSource(reservationInfo));
	}

	@Transactional(readOnly = true)
	public int getReservationInfoId(Reservation reservationInfo) {
		String sql = "SELECT id "
			+ "FROM "
			+ "reservation_info "
			+ "WHERE "
			+ "product_id = :productId "
			+ "AND display_info_id = :displayInfoId "
			+ "AND reservation_name = :reservationName "
			+ "AND reservation_tel = :reservationTelephone "
			+ "AND reservation_email = :reservationEmail "
			+ "AND reservation_date = :reservationYearMonthDay ";

		return jdbc.queryForObject(sql, new BeanPropertySqlParameterSource(reservationInfo), Integer.class);
	}

	public void setReservationPrice(ReservationPrice reservationInfoPrice) {
		String sql = "Insert Into "
			+ "reservation_info_price"
			+ "(reservation_info_id"
			+ ", product_price_id"
			+ ", count) "
			+ "VALUES"
			+ "(:reservationInfoId"
			+ ", :productPriceId"
			+ ", :count)";

		jdbc.update(sql, new BeanPropertySqlParameterSource(reservationInfoPrice));
	}
}