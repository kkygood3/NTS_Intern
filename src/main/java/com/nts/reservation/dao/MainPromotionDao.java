/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.MainPromotion;
import com.nts.reservation.dto.queries.MainPromotionQueries;

/**
 * DB내의 promotion_api_view를 조회하는 DAO. /api/promotions 요청에 대응
 */
@Repository
public class MainPromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPromotion> rowMapper = BeanPropertyRowMapper.newInstance(MainPromotion.class);

	public MainPromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPromotion> selectPromotions(long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(MainPromotionQueries.SELECT_PROMOTION, params, rowMapper);
	}

	public Integer selectCount() {
		return jdbc.queryForObject(MainPromotionQueries.SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
	}
}
