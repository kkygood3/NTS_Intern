/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.dao;

import static com.nts.reservation.category.dao.queries.MainCategoryQueries.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.category.dto.MainCategory;

@Repository
public class MainCategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainCategory> rowMapper = BeanPropertyRowMapper.newInstance(MainCategory.class);

	public MainCategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainCategory> selectPagingCategories() {
		return jdbc.query(SELECT_CATEGORY_PAGE, rowMapper);
	}

}
