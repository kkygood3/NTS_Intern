/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao.productdao;

import static com.nts.dao.productdao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.productdto.Product;

/**
 *
 * @description : ProductDao
 * @package : com.nts.dao
 * @filename : ProductDao.java
 * @author : 최석현
 * @method : ProductDao(DataSource dataSource)
 * @method : List<Product> selectPagingById(int categoryId, int start)
 * @method : int selectCount(int categoryId)
 * 
 */
@Repository
public class ProductDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);


	public List<Product> selectProductsById(int categoryId, int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);

		return jdbc.query(SELECT_PRODUCTS + BY_ID + LIMIT_4, params, rowMapper);
	}

	public List<Product> selectProductsAll(int start) {
		Map<String, ?> param = Collections.singletonMap("start", start);

		return jdbc.query(SELECT_PRODUCTS + LIMIT_4, param, rowMapper);
	}

	public int selectProductCountAll() {

		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public int selectProductCountById(int categoryId) {
		Map<String, ?> param = Collections.singletonMap("categoryId", categoryId);

		return jdbc.queryForObject(SELECT_COUNT + BY_ID, param, Integer.class);
	}
}