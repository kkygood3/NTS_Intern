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

import com.nts.constant.DaoQueries;
import com.nts.dto.Product;

@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAllProducts() {
		return jdbc.query(DaoQueries.PRODUCT_SELECT_ALL, Collections.emptyMap(), rowMapper);
	}

	public Product selectById(long id) {
		Map<String, Long> params = new HashMap<>();
		params.put("id", id);
		return jdbc.query(DaoQueries.PRODUCT_SELECT_BY_ID, params, rowMapper).get(0);
	}

	public List<Product> selectPagingProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(DaoQueries.PRODUCT_SELECT_PAGING, params, rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(DaoQueries.PRODUCT_SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

}
