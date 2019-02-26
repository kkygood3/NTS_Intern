/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.dao.impl;

import static com.nts.reservation.category.dao.query.CategoryDaoQuerys.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Category> selectCategories() {
		return jdbc.query(SELECT_CATEGORY, rowMapper);
	}
}
