/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dao.sqls.ProductDaoSqls;
import com.nts.reservation.product.dto.Product;

@Repository
public class ProductDao {
	// TODO 중복되는 code 줄여보기 (NamedParameterJdbcTemplate, Constructor, RowMapper)
	private NamedParameterJdbcTemplate jdbc;

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	// ResultSet 에서 값을 추출하여 원하는 객체로 변환
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	private static final int ALL_CATEGORIES = 0;

	public List<Product> selectProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		if (categoryId == ALL_CATEGORIES) {
			return jdbc.query(ProductDaoSqls.GET_ALL_PRODUCTS, params, rowMapper);
		} else {
			return jdbc.query(ProductDaoSqls.GET_PRODUCTS_BY_CATEGORY, params, rowMapper);
		}
	}

	public int selectProductCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);

		if (categoryId == ALL_CATEGORIES) {
			return jdbc.queryForObject(ProductDaoSqls.GET_ALL_PRODUCTS_COUNT, params, Integer.class);
		} else {
			return jdbc.queryForObject(ProductDaoSqls.GET_PRODUCTS_COUNT_BY_CATEGORY, params, Integer.class);
		}
	}
}
