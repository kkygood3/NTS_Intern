/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.MainProduct;
import com.nts.reservation.queries.main.MainProductQueries;

/**
 * DB내의 product_api_view를 조회하는 DAO. /api/products 요청에 대응
 */
@Repository
public class MainProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainProduct> rowMapper = BeanPropertyRowMapper.newInstance(MainProduct.class);

	public MainProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainProduct> selectPagingProducts(long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainProductQueries.SELECT_PRODUCT_PAGE, params, rowMapper);
	}

	public List<MainProduct> selectPagingProductsByCategory(long categoryId, long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(MainProductQueries.SELECT_PRODUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	public int selectCountByCategory(long categoryId) {
		Map<String, Long> params = new HashMap<>();
		params.put("categoryId", categoryId);
		Integer count = jdbc.queryForObject(MainProductQueries.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
		if(count == null) {
			count = 0;
		}
		
		return count;
	}

	public int selectCount() {
		Integer count = jdbc.queryForObject(MainProductQueries.SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
		if(count == null) {
			count = 0;
		}
		
		return count;
	}
}
