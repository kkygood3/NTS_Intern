/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfoPriceDto;

/**
 * 예약 정보 가격 DAO
 * @author jinwoo.bae
 */
@Repository
public class ReservationInfoPriceDao extends BasicDao<ReservationInfoPriceDto> {
	private SimpleJdbcInsert insertAction;

	public ReservationInfoPriceDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(ReservationInfoPriceDto.class);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price")
			.usingGeneratedKeyColumns("id");
	}

	public int insert(ReservationInfoPriceDto reservationInfoPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
		return insertAction.executeAndReturnKey(params).intValue();
	}

}
