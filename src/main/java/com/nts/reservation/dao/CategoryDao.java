/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.CategoryDaoSqls.SELECT_CATEGORY;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CategoryDto;

/**
 * @author 육성렬
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/*
	 * @desc 모든 카테고리의 id, name, count (카테고리에 해당하는 프로덕트 갯수)를 DB에 요청한다.
	 * @return List<CategoryDto>
	 */
	public List<CategoryDto> selectCategory() {
		return jdbc.query(SELECT_CATEGORY, Collections.emptyMap(), rowMapper);
	}
}
