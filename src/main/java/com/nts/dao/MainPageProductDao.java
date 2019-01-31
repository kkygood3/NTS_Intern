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

import com.nts.dto.MainPageProduct;
import com.nts.dto.queries.MainPageProductQueries;

@Repository
public class MainPageProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPageProduct> rowMapper = BeanPropertyRowMapper.newInstance(MainPageProduct.class);

	public MainPageProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPageProduct> selectAllPagingProducts(long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainPageProductQueries.PRODUCT_API_SELECT_ALL, params, rowMapper);
	}

	public List<MainPageProduct> selectPagingProducts(long categoryId, long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainPageProductQueries.PRODUCT_API_SELECT_CATEGORY, params, rowMapper);
	}

	public int selectCount(long categoryId) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(MainPageProductQueries.PRODUCT_API_SELECT_COUNT, params, Integer.class);
	}

	public int selectCountAll() {
		return jdbc.queryForObject(MainPageProductQueries.PRODUCT_API_SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
}
