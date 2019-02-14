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

	public void setReservation(Reservation reserveInfo) {
		//		System.out.println(reserveInfo);

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

		jdbc.update(sql, new BeanPropertySqlParameterSource(reserveInfo));
	}
}