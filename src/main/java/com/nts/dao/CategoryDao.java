/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.Category;

import static com.nts.dao.CategoryDaoSqls.*;


/**
 *
 * @description : CategoryDao
 * @package : com.nts.dao
 * @filename : CategoryDao.java
 * @author : 최석현
 * @method : CategoryDao(DataSource dataSource)
 * @method : List<Category> selectGroup()
 * @method : List<Integer> selectCounts()
 * 
 */
@Repository
public class CategoryDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public List<Category> selectCategoriesGroupById() {
		return jdbc.query(SELECT_CATEGORIES_GROUP_BY_ID, rowMapper);
	}
}
