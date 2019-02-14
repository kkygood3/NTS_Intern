/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dao.sqls.ProductDaoSqls;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

@Repository
public class ProductDaoImpl implements ProductDao {
	private NamedParameterJdbcTemplate jdbc;

	public ProductDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	private static final int ALL_CATEGORIES = 0;

	@Override
	public List<Product> selectProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		if (categoryId == ALL_CATEGORIES) {
			return jdbc.query(ProductDaoSqls.GET_ALL_PRODUCTS, params, productRowMapper);
		} else {
			return jdbc.query(ProductDaoSqls.GET_PRODUCTS_BY_CATEGORY, params, productRowMapper);
		}
	}

	@Override
	public int selectProductCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);

		if (categoryId == ALL_CATEGORIES) {
			return jdbc.queryForObject(ProductDaoSqls.GET_ALL_PRODUCTS_COUNT, params, Integer.class);
		} else {
			return jdbc.queryForObject(ProductDaoSqls.GET_PRODUCTS_COUNT_BY_CATEGORY, params, Integer.class);
		}
	}

	@Override
	public List<ProductImage> selectProductImages(int displayInfoId, String type) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		param.put("type", type);
		if (type.equals("ma")) {
			return jdbc.query(ProductDaoSqls.GET_PRODUCT_IMAGES, param, productImageRowMapper);
		} else {
			return jdbc.query(ProductDaoSqls.GET_PRODUCT_ECT_IMAGES, param, productImageRowMapper);
		}
	}

	@Override
	public List<ProductPrice> selectProductPrices(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.query(ProductDaoSqls.GET_PRODUCT_PRICES, param, productPriceRowMapper);
	}
}
