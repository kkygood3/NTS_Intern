package com.nts.reservation.dao;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import static com.nts.reservation.dao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Repository
public class ProductDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<Promotion> getPromotions() {
		return jdbc.query(SELECT_PROMOTION, BeanPropertyRowMapper.newInstance(Promotion.class));
	}

	public List<Product> getProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCTS_PAGING, params, BeanPropertyRowMapper.newInstance(Product.class));
	}

	public List<Product> getProductsByCategory(int start, int category, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("category_id", category);
		return jdbc.query(SELECT_ALL_PRODUCTS_BY_CATEGORY_PAGING, params, BeanPropertyRowMapper.newInstance(Product.class));
	}

	public Long getProductsCount() {
		return jdbc.queryForObject(SELECT_PRODUCTS_COUNT, Collections.emptyMap(), Long.class);
	}

	public List<Category> getProductsCountByCategory() {
		return jdbc.query(SELECT_PRODUCTS_COUNT_BY_CATEGORY, BeanPropertyRowMapper.newInstance(Category.class));
	}
}
