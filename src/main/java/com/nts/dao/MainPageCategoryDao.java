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

import com.nts.dto.queries.MainPageCategoryQueries;

@Repository
public class MainPageCategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPageCategoryQueries> rowMapper = BeanPropertyRowMapper.newInstance(MainPageCategoryQueries.class);

	public MainPageCategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPageCategoryQueries> selectAllCategories() {
		return jdbc.query(MainPageCategoryQueries.SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
