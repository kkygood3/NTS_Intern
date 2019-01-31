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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.dto.Category;
import com.nts.dto.Promotion;
import com.nts.dto.queries.PromotionQueries;

/**
 * Promotion Table을 조회하는 DTO
 */
@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCount() {
		return jdbc.queryForObject(PromotionQueries.SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<Promotion> selectPromotions(long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(PromotionQueries.SELECT_PROMOTIONS, params, rowMapper);
	}
}
