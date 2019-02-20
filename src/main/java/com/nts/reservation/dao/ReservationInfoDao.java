/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationDisplayInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoDto;

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
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
	}

	public int insert(ReservationInfoDto reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	public List<ReservationDisplayInfoDto> selectReservationDisplayInfos(String reservationEmail) {
		return jdbcTemplate.query(SELECT_RESERVATION_DISPLAY_INFOS,
			Collections.singletonMap("reservationEmail", reservationEmail),
			BeanPropertyRowMapper.newInstance(ReservationDisplayInfoDto.class));
	}

	public void updateReservationToCancel(int reservationId) {
		jdbcTemplate.update(UPDATE_RESERVATION_TO_CANCEL, Collections.singletonMap("reservationId", reservationId));
	}
}
