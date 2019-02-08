/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sqls.ProductDaoSqls;
import com.nts.reservation.dto.Product;

@Repository
public class ProductDao {
	// 이름이 붙여진 파라미터가 들어간 SQL을 호출 (? -> :id로 사용 가능)
	private NamedParameterJdbcTemplate jdbc;

	private static final int ALL_CATEGORIES = 0;

	// ResultSet 에서 값을 추출하여 원하는 객체로 변환
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * category별 product를 가져옴
	 * @author yongjoon.Park
	 * @return
	 */
	public List<Product> getProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		if (categoryId == ALL_CATEGORIES) {
			return jdbc.query(ProductDaoSqls.GET_ALL_PRODUCTS, params, rowMapper);
		} else {
			return jdbc.query(ProductDaoSqls.GET_PRODUCTS_BY_CATEGORY, params, rowMapper);
		}
	}

	/**
	 * category별 product의 개수를 가져옴
	 * @author yongjoon.Park
	 * @return
	 */
	public int getProductCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		
		if (categoryId == ALL_CATEGORIES) {
			return jdbc.queryForObject(ProductDaoSqls.GET_ALL_PRODUCTS_COUNT, params, Integer.class);
		} else {
			return jdbc.queryForObject(ProductDaoSqls.GET_PRODUCTS_COUNT_BY_CATEGORY, params, Integer.class);
		}
	}
}
