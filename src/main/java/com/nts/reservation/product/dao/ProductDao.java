/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao;

import static com.nts.reservation.product.dao.querys.ProductQuerys.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Product> productMapper;

	/**
	 * product 전체목록을 조회하여  Product List 반환
	 */
	public List<Product> selectAllCategoryProductList(int start) {
		Map<String, Integer> param = Collections.singletonMap("start", start);

		return jdbcTemplate.query(SELECT_ALL_CATEGORY_PRODUCT_LIST, param, productMapper);
	}

	/**
	 * product 전체목록의 count 반환
	 */
	public int selectAllCategoryProductCount() {
		return jdbcTemplate.queryForObject(SELECT_ALL_CATEGORY_PRODUCTS_COUNT, Collections.emptyMap(), Integer.class);
	}

	/**
	 * 특정 category의 product 목록을 조회하여 Product List 반환
	 */
	public List<Product> selectOneCategoryProductList(int categoryId, int start) {
		Map<String, Object> param = new HashMap<>();
		param.put("categoryId", categoryId);
		param.put("start", start);

		return jdbcTemplate.query(SELECT_ONE_CATEGORY_PRODUCT_LIST, param, productMapper);
	}

	/**
	 * product 특정목록의 count 반환
	 */
	public int selectOneCategoryProductCount(int categoryId) {
		Map<String, Integer> param = Collections.singletonMap("categoryId", categoryId);

		return jdbcTemplate.queryForObject(SELECT_ONE_CATEGORY_PRODUCTS_COUNT, param, Integer.class);
	}
}
