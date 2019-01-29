package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.CategoryApiView;

@Repository
public class CategoryApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryApiView> rowMapper = BeanPropertyRowMapper.newInstance(CategoryApiView.class);

	private String SELECT_ALL = "SELECT * FROM category_api_view";

	public CategoryApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CategoryApiView> selectAllCategories() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
