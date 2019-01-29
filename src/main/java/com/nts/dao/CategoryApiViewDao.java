/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.constant.DaoQueries;
import com.nts.dto.CategoryApiView;

@Repository
public class CategoryApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryApiView> rowMapper = BeanPropertyRowMapper.newInstance(CategoryApiView.class);

	public CategoryApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CategoryApiView> selectAllCategories() {
		return jdbc.query(DaoQueries.CATEGORY_API_SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
