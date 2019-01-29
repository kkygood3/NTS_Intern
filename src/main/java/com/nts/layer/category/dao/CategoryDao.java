/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.layer.category.dto.Category;

import static com.nts.layer.category.sqls.CategorySqls.*;

/**
 * @author 전연빈
 */
@Repository
public class CategoryDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	/**
	 * @desc 카테고리들 가져오기
	 * @return categories
	 */
	public List<Category> selectCategories() {
		return namedParameterJdbcTemplate.query(SELECT_CATEGORIES, rowMapper);
	}
}
