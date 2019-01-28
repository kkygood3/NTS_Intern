/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import static com.nts.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.CategoryDto;

/**
 * 카테고리 데이터를 가져오는 클래스  
 * @author jinwoo.bae
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * 카테고리 테이블의 모든정보들을 select한다 
	 */
	public List<CategoryDto> selectAll() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}