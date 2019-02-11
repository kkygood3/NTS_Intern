/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.queries.MainCategoryQueries;
import com.nts.reservation.dto.MainCategory;

/**
 * DB내의 category_api_view를 조회하는 DAO. /api/categories 요청에 대응
 */
@Repository
public class MainCategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainCategory> rowMapper = BeanPropertyRowMapper.newInstance(MainCategory.class);

	public MainCategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainCategory> selectCategories(long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(MainCategoryQueries.SELECT_CATEGORIES, params, rowMapper);
	}
}
