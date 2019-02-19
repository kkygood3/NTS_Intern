/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.reservation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.dto.reservation.ReservationParameter;
import com.nts.dto.reservation.Reservation;
import com.nts.dto.reservation.ReservationPrice;

import static com.nts.sqls.reservation.ReservationSqls.*;

@Repository
public class ReservationRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private RowMapper<Reservation> reservationInfoRowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	/**
	 * @desc reservationEmail 별 reservationInfo 가져오기
	 * @param reservationEmail
	 * @return List<ReservationInfo>
	 */
	public List<Reservation> selectReservationInfoByReservationEmail(String reservationEmail) {

		Map<String, Object> params = Collections.singletonMap("reservationEmail", reservationEmail);

		return namedParameterJdbcTemplate.query(SELECT_RESERVATION_INFO_BY_RESERVATION_EMAIL, params,
				reservationInfoRowMapper);
	}
	
	/**
	 * @desc reservation 삽입
	 * @param reservationParameter
	 * @return	reservationInfoId
	 */
	public Long insertReservation(ReservationParameter reservationParameter) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(reservationParameter);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT_INTO_RESERVATION_INFO, sqlParameterSource, keyHolder);

		return keyHolder.getKey().longValue();
	}

	/**
	 * @desc
	 * @param reservationPrice
	 * @return 성공 여부 (1, 0)
	 */
	public int insertReservationPrice(ReservationPrice reservationPrice) {

		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(reservationPrice);

		return namedParameterJdbcTemplate.update(INSERT_INTO_RSERVATION_INFO_PRICE, sqlParameterSource);
	}

	/**
	 * @desc reservationCancelFlag 변경
	 * @param reservationId
	 * @param cancelFlag
	 * @return 성공 여부 (1 , 0)
	 */
	public int updateReservationCancelFlag(long reservationId, int cancelFlag) {

		Map<String, Object> params = new HashMap<>();
		params.put("reservationId", reservationId);
		params.put("cancelFlag", cancelFlag);

		return namedParameterJdbcTemplate.update(UPDATE_RESERVATION_INFO_CANCEL, params);
	}
}
