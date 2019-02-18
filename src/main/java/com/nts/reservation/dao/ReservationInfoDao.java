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

import com.nts.reservation.dto.ReservationInfoDto;

/**
 * 예약 정보 DAO
 * @author jinwoo.bae
 */
@Repository
public class ReservationInfoDao extends BasicDao<ReservationInfoDto> {
	private SimpleJdbcInsert insertAction;

	public ReservationInfoDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(ReservationInfoDto.class);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
	}

	public int insert(ReservationInfoDto reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
}
