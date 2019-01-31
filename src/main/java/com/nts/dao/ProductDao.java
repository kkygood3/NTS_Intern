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

import com.nts.dto.Product;
import com.nts.dto.queries.ProductQueries;

/**
 * Product Table을 조회하는 DTO
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Product selectProductsById(long id, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("id", id);
		params.put("limit", limit);
		return jdbc.query(ProductQueries.SELECT_PRODUCT_BY_ID, params, rowMapper).get(0);
	}

	public List<Product> selectProductsPage(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(ProductQueries.SELECT_PRODUCT_PAGING, params, rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(ProductQueries.SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}

}
