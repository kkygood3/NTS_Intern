/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.ReservationSqls.COUNT_BY_CATEGORY_ID;
import static com.nts.reservation.dao.ReservationSqls.GET_PRODUCTS;
import static com.nts.reservation.dao.ReservationSqls.GET_PRODUCTS_BY_CATEGORY_ID;
import static com.nts.reservation.dao.ReservationSqls.LIMIT;
import static com.nts.reservation.dao.ReservationSqls.SELECT_COUNT_ALL;

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

	public int selectAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public int selectByCategory(int categoryId) {
		Map<String, Integer> param = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(COUNT_BY_CATEGORY_ID, param, Integer.class);
	}

	public List<Product> getProducts(int categoryId, int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		if (categoryId != 0) {
			params.put("categoryId", categoryId);
			return jdbc.query(GET_PRODUCTS_BY_CATEGORY_ID + LIMIT, params, rowMapper);
		}
		return jdbc.query(GET_PRODUCTS + LIMIT, params, rowMapper);
	}
}
