/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.dao;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.layer.category.dto.CategoryDto;

import static com.nts.layer.category.sqls.CategorySqls.*;

/**
 * @author 전연빈
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryDao(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 카테고리들 가져오기
	 * @return categories
	 */
	public List<CategoryDto> selectCategories() {
		return namedParameterJdbcTemplate.query(SELECT_CATEGORIES, Collections.emptyMap(), rowMapper);
	}
}
