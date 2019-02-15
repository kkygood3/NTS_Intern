/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.review;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.DetailSqls;
import com.nts.reservation.dao.sql.ReviewSqls;
import com.nts.reservation.dto.detail.DetailDisplayInfo;
import com.nts.reservation.dto.review.ReviewDisplayInfo;

/**
 * /api/products/{displayInfoId}/review 요청에 대응
 */
@Repository
public class ReviewDisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReviewDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReviewDisplayInfo.class);

	public ReviewDisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * review 페이지를 로드할 때 DisplayInfo 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public ReviewDisplayInfo selectDetailDisplayInfoByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(ReviewSqls.SELECT_REVIEW_DISPLAY_INFO, params, rowMapper);
	}
}
