/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.querys.CategoryQuerys;
import com.nts.reservation.model.Category;

@Repository
public class CategoryDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Category> categoryMapper;

	/**
	 * category 목록을 조회하여 List 객체로 반환
	 */
	public List<Category> getCategoryList() {
		return jdbcTemplate.query(CategoryQuerys.SELECT_CATEGORY_LIST, categoryMapper);
	}

}
