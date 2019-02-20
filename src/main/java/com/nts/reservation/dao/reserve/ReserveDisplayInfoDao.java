/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.ReserveSqls;
import com.nts.reservation.dto.reserve.ReserveDisplayInfo;

/**
 * reserve 페이지 로드시 사용하는 상품 전시 정보
 */
@Repository
public class ReserveDisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReserveDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReserveDisplayInfo.class);

	public ReserveDisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * reserve 페이지를 로드할 때 DisplayInfo 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public ReserveDisplayInfo selectReviewDisplayInfoByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(ReserveSqls.SELECT_RESERVE_DISPLAY_INFO, params, rowMapper);
	}
}
