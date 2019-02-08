/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.queries.MainProductQueries.*;

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

@Repository
public class MainProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainProduct> rowMapper = BeanPropertyRowMapper.newInstance(MainProduct.class);

	public MainProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainProduct> selectPagingProducts(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PROUDUCT_PAGE, params, rowMapper);
	}

	public List<MainProduct> selectPagingProductsByCategory(Integer categoryId, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PROUDUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public int selectCountByCategory(Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

}
