/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.category;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.category.Category;

import static com.nts.sqls.category.CategorySqls.*;

/**
 * @author 전연빈
 */
@Repository
public class CategoryRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	/**
	 * @desc 카테고리들 가져오기
	 * @return categories
	 */
	public List<Category> selectCategories() {
		return namedParameterJdbcTemplate.query(SELECT_CATEGORIES, rowMapper);
	}
}
