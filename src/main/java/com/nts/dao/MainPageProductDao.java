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

/**
 * DB내의 product_api_view를 조회하는 DAO. /api/products 요청에 대응
 */
@Repository
public class MainPageProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPageProduct> rowMapper = BeanPropertyRowMapper.newInstance(MainPageProduct.class);

	public MainPageProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPageProduct> selectPagingProducts(long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainPageProductQueries.SELECT_PRODUCTS_PAGE, params, rowMapper);
	}

	public List<MainPageProduct> selectPagingProductsByCategory(long categoryId, long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainPageProductQueries.SELECT_PRODUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	public int selectCountByCategory(long categoryId) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(MainPageProductQueries.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	public int selectCount() {
		return jdbc.queryForObject(MainPageProductQueries.SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
