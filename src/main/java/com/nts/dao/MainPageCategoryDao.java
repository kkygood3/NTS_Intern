/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.MainPageCategory;
import com.nts.dto.queries.MainPageCategoryQueries;

/**
 * DB내의 category_api_view를 조회하는 DAO. /api/categories 요청에 대응
 */
@Repository
public class MainPageCategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPageCategory> rowMapper = BeanPropertyRowMapper.newInstance(MainPageCategory.class);

	public MainPageCategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPageCategory> selectCategories(long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(MainPageCategoryQueries.SELECT_CATEGORIES, params, rowMapper);
	}
}
