/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.MainPagePromotion;
import com.nts.dto.queries.MainPagePromotionQueries;

/**
 * DB내의 promotion_api_view를 조회하는 DAO. /api/promotions 요청에 대응
 */
@Repository
public class MainPagePromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPagePromotion> rowMapper = BeanPropertyRowMapper.newInstance(MainPagePromotion.class);

	public MainPagePromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPagePromotion> selectPromotions(long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(MainPagePromotionQueries.SELECT_PROMOTION, params, rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(MainPagePromotionQueries.SELECT_PROMOTION_COUNT, Collections.emptyMap(),
			Integer.class);
	}
}
