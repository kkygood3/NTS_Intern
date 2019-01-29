package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.Category;

@Repository
public class CategoryDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	private String SELECT_ALL = "SELECT * FROM category";

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectAllCategories() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
