/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_PRODUCT;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_PRODUCT_BY_CATEGORY;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_PRODUCT;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_PRODUCT_BY_CATEGORY;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_PROMOTION_PRODUCT;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductDto;

/**
 * @author 육성렬
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 카테고리와 상관없이 모든 프로덕트들을 start에서부터 limit개 만큼 DB에 요청한다.
	 * @param start
	 * @param limit
	 * @return List<ProductDto>
	 */
	public List<ProductDto> selectProduct(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT, params, rowMapper);
	}

	/**
	 * @desc 정해진 카테고리에 해당하는 프로덕트들을 start에서부터 limit개만큼 DB에 요청한다.
	 * @param start
	 * @param limit
	 * @param categoryId
	 * @return List<ProductDto>
	 */
	public List<ProductDto> selectProductByCategory(int start, int limit, int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, rowMapper);
	}

	/**
	 * @desc 프로모션이 진행 중인 모든 프로덕트를 DB에 요청한다.
	 * @return List<ProductDto>
	 */
	public List<ProductDto> selectPromotionProduct() {
		return jdbc.query(SELECT_PROMOTION_PRODUCT, Collections.emptyMap(), rowMapper);
	}

	/**
	 * @desc 모든 프로덕트의 갯수를 DB에 요청한다.
	 * @return Integer 프로덕트의 갯수
	 */
	public int countAll() {
		return jdbc.queryForObject(SELECT_COUNT_PRODUCT, Collections.emptyMap(), Integer.class);
	}

	/**
	 * @desc 카테고리에 해당하는 프로덕트의 갯수를 DB에 요청한다.
	 * @param categoryId
	 * @return 카테고리에 해당하는 프로덕트의 갯수
	 */
	public int countByCategory(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("category", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_PRODUCT_BY_CATEGORY, params, Integer.class);
	}
}
