/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductPriceDto;

/**
 * 상품 정보들을 가져오는 클래스
 * @author jinwoo.bae
 */
@Repository
public class ProductDao extends BasicDao<ProductDto> {
	public ProductDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(ProductDto.class);
	}

	/**
	 * 모든 카테고리의 상품들을 가져온다 
	 */
	public List<ProductDto> selectProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_PRODUCTS, params, rowMapper);
	}

	/**
	 * 특정 카테고리의 상품들을 가져온다.
	 */
	public List<ProductDto> selectProductsByCategoryId(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	}

	/**
	 * 모든상품의 총 개수를 가져온다 
	 */
	public int selectCount() {
		return jdbcTemplate.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	/**
	 * 특정 카테고리의 총개수를 가져온다.
	 */
	public int selectCountByCategoryId(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbcTemplate.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}

	/**
	 * 프로덕트 ID에 해당하는 프로덕트를 select
	 */
	public ProductDto selectProduct(int productId) {
		return jdbcTemplate.queryForObject(SELECT_PRODUCT, Collections.singletonMap("productId", productId),
			rowMapper);
	}

	/**
	 * 프로덕트 ID에 해당하는 가격리스트들 select 
	 */
	public List<ProductPriceDto> selectProductPrices(int productId) {
		return jdbcTemplate.query(SELECT_PRODUCT_PRICES, Collections.singletonMap("productId", productId),
			BeanPropertyRowMapper.newInstance(ProductPriceDto.class));
	}

}