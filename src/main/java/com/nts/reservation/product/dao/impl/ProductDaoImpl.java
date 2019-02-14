/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.impl;

import static com.nts.reservation.product.dao.query.ProductDaoQuerys.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class ProductDaoImpl implements ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

	public ProductDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Product> selectProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT, params, productRowMapper);
	}

	@Override
	public List<Product> selectProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, productRowMapper);
	}

	@Override
	public int selectProductsCount() {
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}

	@Override
	public int selectProductsCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	@Override
	public List<ProductImage> selectProductImageByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("DisplayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGE_BY_DISPLAY_INFO_ID, params, productImageRowMapper);
	}
}
