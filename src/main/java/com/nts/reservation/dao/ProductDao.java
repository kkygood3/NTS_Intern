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
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.querys.ProductQuerys;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductRequest;
import com.nts.reservation.model.ProductResponse;

@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Product> productMapper;

	@Transactional(readOnly = true)
	public ProductResponse getAllCategoryProductResponse(ProductRequest productRequest) {

		List<Product> productList = jdbcTemplate.query(ProductQuerys.SELECT_ALL_CATEGORY_PRODUCT_LIST,
			new BeanPropertySqlParameterSource(productRequest),
			productMapper);
		Integer productCount = jdbcTemplate.queryForObject(ProductQuerys.SELECT_ALL_CATEGORY_PRODUCTS_COUNT,
			Collections.emptyMap(), Integer.class);

		return new ProductResponse(productList, productCount);
	}

	@Transactional(readOnly = true)
	public ProductResponse getOneCategoryProductResponse(ProductRequest productRequest) {
		List<Product> productList = jdbcTemplate.query(ProductQuerys.SELECT_ONE_CATEGORY_PRODUCT_LIST,
			new BeanPropertySqlParameterSource(productRequest),
			productMapper);
		Integer productCount = jdbcTemplate.queryForObject(ProductQuerys.SELECT_ONE_CATEGORY_PRODUCTS_COUNT,
			new BeanPropertySqlParameterSource(productRequest),
			Integer.class);

		return new ProductResponse(productList, productCount);
	}
}
