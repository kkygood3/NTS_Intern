/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ProductSqls.LIMIT;
import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_COUNT_ALL;
import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_COUNT_BY_CATEGORY_ID;
import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_PRODUCTS;
import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_PRODUCTS_BY_CATEGORY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;

/**
* @author  : 이승수
*/
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCountAllProducts() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public int selectByCategory(int categoryId) {
		Map<String, Integer> param = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, param, Integer.class);
	}

	public List<Product> selectProducts(Integer categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		if (categoryId != null) {
			params.put("categoryId", categoryId);
			return jdbc.query(SELECT_PRODUCTS_BY_CATEGORY_ID + LIMIT, params, rowMapper);
		}
		return jdbc.query(SELECT_PRODUCTS + LIMIT, params, rowMapper);
	}
}