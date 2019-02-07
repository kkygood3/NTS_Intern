/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.product.Product;

import static com.nts.sqls.product.ProductSqls.*;

/**
 * @author 전연빈
 */
@Repository
public class ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private static final int LIMIT = 4;

	/**
	 * @desc 카테고리별 데이터 가져오기
	 * @param categoryId
	 * @param start
	 * @return list(Product)
	 */
	public List<Product> selectProductsByCategory(int categoryId, int start) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("categoryId", categoryId);
		params.put("start", start * LIMIT);
		params.put("limit", LIMIT);

		return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_CATEGORY, params, rowMapper);
	}

	/**
	 * @desc 카테고리 별 카운트 갯수
	 * @param categoryId
	 * @return totalCount
	 */
	public int selectProductsCountByCategory(int categoryId) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("categoryId", categoryId);

		return namedParameterJdbcTemplate.queryForObject(SELECT_PRODUCTS_COUNT_BY_CATEGORY, params, Integer.class);
	}

	/**
	 * @desc 상품 전체 가져오기
	 * @param categoryId
	 * @param start
	 * @return list(Product)
	 */
	public List<Product> selectProducts(int start) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("start", start * LIMIT);
		params.put("limit", LIMIT);

		return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_ALL, params, rowMapper);
	}

	/**
	 * @desc 상품 총 카운트 갯수
	 * @return totalCount
	 */
	public int selectProductsCount() {

		return (int)namedParameterJdbcTemplate.queryForObject(SELECT_PRODUCTS_COUNT_ALL, Collections.EMPTY_MAP,Integer.class);
	}
}
