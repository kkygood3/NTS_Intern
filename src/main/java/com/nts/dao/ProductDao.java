/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.Product;
import static com.nts.dao.ProductDaoSqls.*;


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
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectPagingById(int categoryId, int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
	
		String query = SELECT_PAGING_PREFIX;
		if(categoryId > 0) {
			query += APPEND_CATEGORY_ID;
			query += String.valueOf(categoryId);
		}
		query += SELECT_PAGING_SUFFIX;
		
		return jdbc.query(query, params, rowMapper);
		
	}

	public int selectCount(int categoryId) {
		String query = SELECT_COUNT;
		if(categoryId > 0) {
			query += APPEND_CATEGORY_ID;
			query += String.valueOf(categoryId);
		}
		
		return jdbc.queryForObject(query, Collections.emptyMap(), Integer.class);
	}
}