/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.querys.ProductQuerys;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductRequest;

@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Product> productMapper;

	/**
	 * product 전체목록을 조회하여  Product List 반환
	 */
	public List<Product> getAllCategoryProductList(ProductRequest productRequest) {
		return jdbcTemplate.query(ProductQuerys.SELECT_ALL_CATEGORY_PRODUCT_LIST,
			new BeanPropertySqlParameterSource(productRequest),
			productMapper);
	}

	/**
	 * product 전체목록의 count 반환
	 */
	public int getAllCategoryProductCount(ProductRequest productRequest) {
		return jdbcTemplate.queryForObject(ProductQuerys.SELECT_ALL_CATEGORY_PRODUCTS_COUNT,
			Collections.emptyMap(), Integer.class);
	}

	/**
	 * 특정 category의 product 목록을 조회하여 Product List 반환
	 */
	public List<Product> getOneCategoryProductList(ProductRequest productRequest) {
		return jdbcTemplate.query(ProductQuerys.SELECT_ONE_CATEGORY_PRODUCT_LIST,
			new BeanPropertySqlParameterSource(productRequest),
			productMapper);
	}

	/**
	 * product 특정목록의 count 반환
	 */
	public int getOneCategoryProductCount(ProductRequest productRequest) {
		return jdbcTemplate.queryForObject(ProductQuerys.SELECT_ONE_CATEGORY_PRODUCTS_COUNT,
			new BeanPropertySqlParameterSource(productRequest),
			Integer.class);
	}
}
