/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import static com.nts.dao.CategoryDaoSqls.*;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 카테고리 데이터를 가져오는 클래스  
 * @author jinwoo.bae
 */
@Repository
public class CategoryDao {
	private JdbcTemplate jdbcTemplate;

	public CategoryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 카테고리 목록과 카테고리별 프로덕트의 개수를 구한다. 
	 */
	public List<Map<String, Object>> selectAllWithProductCount() {
		return jdbcTemplate.queryForList(SELECT_ALL_WITH_PRODUCT_COUNT);
	}
}