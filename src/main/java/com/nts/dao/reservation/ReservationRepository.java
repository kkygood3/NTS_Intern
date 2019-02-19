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

import com.nts.dto.product.ProductPrice;
import com.nts.dto.reservation.ReservationData;
import com.nts.dto.reservation.ReservationInfo;
import com.nts.dto.reservation.ReservationPrice;

import static com.nts.sqls.reservation.ReservationSqls.*;

@Repository
public class ReservationRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	public List<ReservationInfo> selectReservationInfoByReservationEmail(String reservationEmail){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reservationEmail", reservationEmail);

		return namedParameterJdbcTemplate.query(SELECT_RESERVATION_INFO_BY_RESERVATION_EMAIL, params, reservationInfoRowMapper);
	}
	
	public Long insertReservation(ReservationData reservationData) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(reservationData);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT_INTO_RESERVATION_INFO, sqlParameterSource,keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
	public int insertReservationPrice(ReservationPrice reservationPrice) {
		
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(reservationPrice);
		
		return namedParameterJdbcTemplate.update(INSERT_INTO_RSERVATION_INFO_PRICE, sqlParameterSource);
	}
	
	public int updateReservationToCancelByReservationId(long reservationId) {
		
		Map<String,Object> params = Collections.singletonMap("reservationId", reservationId);
		return namedParameterJdbcTemplate.update(UPDATE_RESERVATION_INFO_CANCEL, params);
	}
}
