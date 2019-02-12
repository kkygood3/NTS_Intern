/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.dao.impl;

import static com.nts.reservation.category.dao.query.CategoryDaoQuerys.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Category> selectCategories(int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(SELECT_CATEGORY, params, rowMapper);
	}
}
