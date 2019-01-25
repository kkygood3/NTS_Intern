/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.querys.ProductQuerys;
import com.nts.reservation.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Product> productMapper;

	public List<Product> getProductList(int categoryId, int start) {
		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);

		return jdbcTemplate.query(ProductQuerys.SELECT_PRODUCT_LIST, params, productMapper);
	}

	public Integer getCategoryProductsCount(int categoryId) {
		Map<String, ?> params = Collections.singletonMap("categoryId", categoryId);
		return jdbcTemplate.queryForObject(ProductQuerys.SELECT_CATEGORY_PRODUCTS_COUNT, params,
			Integer.class);
	}
}
