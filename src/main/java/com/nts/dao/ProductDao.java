/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import static com.nts.dao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.ProductDto;

/**
 * 상품 정보들을 가져오는 클래스
 * @author jinwoo.bae
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * 모든 카테고리의 상품들을 가져온다 
	 */
	public List<ProductDto> selectAll(int start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_ALL, params, rowMapper);
	}

	/**
	 * 특정 카테고리의 상품들을 가져온다.
	 */
	public List<ProductDto> selectByCategoryId(int categoryId, int start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
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

}