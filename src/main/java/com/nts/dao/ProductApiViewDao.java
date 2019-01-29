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

import com.nts.constant.DaoQueries;
import com.nts.dto.ProductApiView;

@Repository
public class ProductApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductApiView> rowMapper = BeanPropertyRowMapper.newInstance(ProductApiView.class);

	public ProductApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductApiView> selectAllPagingProducts(long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(DaoQueries.PRODUCT_API_SELECT_ALL, params, rowMapper);
	}

	public List<ProductApiView> selectPagingProducts(long categoryId, long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("category_id", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(DaoQueries.PRODUCT_API_SELECT_CATEGORY, params, rowMapper);
	}

	public int selectCount(long categoryId) {
		Map<String, Long> params = new HashMap<>();
		params.put("category_id", categoryId);
		return jdbc.queryForObject(DaoQueries.PRODUCT_API_SELECT_COUNT, params, Integer.class);
	}

	public int selectCountAll() {
		return jdbc.queryForObject(DaoQueries.PRODUCT_API_SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
}
