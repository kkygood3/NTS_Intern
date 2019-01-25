/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

import static com.nts.reservationservice.dao.ProductDaoSqls.SELECT_COUNT_PRODUCT;
import static com.nts.reservationservice.dao.ProductDaoSqls.SELECT_COUNT_PRODUCT_BY_CATEGORY;
import static com.nts.reservationservice.dao.ProductDaoSqls.SELECT_PRODUCT;
import static com.nts.reservationservice.dao.ProductDaoSqls.SELECT_PRODUCT_BY_CATEGORY;
import static com.nts.reservationservice.dao.ProductDaoSqls.SELECT_PRODUCT_BY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservationservice.dto.ProductDto;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductDto> selectProduct(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT, params, rowMapper);
	}

	public List<ProductDto> selectProductByCategory(int start, int limit, int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, rowMapper);
	}

	public ProductDto selectProductById(Long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_PRODUCT_BY_ID, params, rowMapper);
	}

	public int countAll() {
		return jdbc.queryForObject(SELECT_COUNT_PRODUCT, Collections.emptyMap(), Integer.class);
	}

	public int countByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_PRODUCT_BY_CATEGORY, params, Integer.class);
	}
}
