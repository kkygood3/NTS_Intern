package com.nts.reservation.category.dao.daoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dao.sqls.CategoryDaoSqls;
import com.nts.reservation.category.dto.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Category> selectCategories() {
		return jdbc.query(CategoryDaoSqls.GET_CATEGORY, rowMapper);
	}
}
