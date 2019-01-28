/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CategoryDto;

/*
 * @author 육성렬
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CategoryDto> selectCategory() {
		return jdbc.query(SELECT_CATEGORY, Collections.emptyMap(), rowMapper);
	}
}
