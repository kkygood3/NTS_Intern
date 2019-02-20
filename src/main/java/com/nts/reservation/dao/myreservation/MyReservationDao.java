/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.myreservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.MyReservationSqls;
import com.nts.reservation.dto.myreservation.MyReservationInfo;

/**
 * /api/reservations에 대응
 */
@Repository
public class MyReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MyReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfo.class);

	public MyReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * myreservation 페이지를 로드할 때 email을 기준으로 예약정보 조회
	 * @param reservationEmail
	 */
	public List<MyReservationInfo> selectMyReservationByEmail(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", email);
		return jdbc.query(MyReservationSqls.SELECT_MY_RESERVATION_INFO, params, rowMapper);
	}

	public Integer updateMyReservationCancelById(Integer reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.update(MyReservationSqls.CANCEL_RESERVE, params);
	}
}
