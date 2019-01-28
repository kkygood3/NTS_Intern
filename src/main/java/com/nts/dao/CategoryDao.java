package com.nts.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.dto.Category;

@Repository
public class CategoryDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	private final String DaoTableName = "category";
	
	private String SELECT_ALL = "SELECT * FROM category";
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName(DaoTableName)
			.usingGeneratedKeyColumns("id");
	}

	public List<Category> selectAll() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
}
