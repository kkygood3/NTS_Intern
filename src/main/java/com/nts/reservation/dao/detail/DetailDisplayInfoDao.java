/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.DetailSqls;
import com.nts.reservation.dto.detail.DetailDisplayInfo;

/**
 * /api/products/{displayInfoId} 요청에 대응
 */
@Repository
public class DetailDisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DetailDisplayInfo.class);

	public DetailDisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * detail 페이지를 로드할 때 DisplayInfo 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public DetailDisplayInfo selectDetailDisplayInfoByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(DetailSqls.SELECT_DETAIL_DISPLAY_INFO, params, rowMapper);
	}
}
