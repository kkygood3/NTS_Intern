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

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class ProductDaoImpl implements ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	private RowMapper<ProductExtraImage> productExtraImageRowMapper = BeanPropertyRowMapper
		.newInstance(ProductExtraImage.class);

	public ProductDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Product> selectProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCT, params, productRowMapper);
	}

	@Override
	public List<Product> selectProductsByCategoryId(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, productRowMapper);
	}

	@Override
	public int selectProductsCount() {
		return jdbc.queryForObject(SELECT_ALL_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}

	@Override
	public int selectProductsCountByCategoryId(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	@Override
	public List<ProductImage> selectProductImage(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGE, params, productImageRowMapper);
	}

	@Override
	public List<ProductPrice> selectProductPrice(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICE, params, productPriceRowMapper);
	}

	@Override
	public ProductExtraImage selectProductExtraImage(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return DataAccessUtils.singleResult(jdbc.query(SELECT_PRODUCT_EXTRA_IMAGE, params, productExtraImageRowMapper));
	}
}
