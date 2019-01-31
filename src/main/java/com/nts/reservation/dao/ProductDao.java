package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_ALL_PAGING;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_BY_CATERORY_PAGING;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_ALL;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_BY_CATEGORY;
import static com.nts.reservation.dto.column.Columns.CATEGORY_ID;
import static com.nts.reservation.dto.column.Columns.LIMIT;
import static com.nts.reservation.dto.column.Columns.START;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put(START, start);
		params.put(LIMIT, limit);

		return jdbc.query(SELECT_ALL_PAGING, params, rowMapper);
	}

	public List<Product> selectByCategory(Category category, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put(CATEGORY_ID, category.getId());
		params.put(START, start);
		params.put(LIMIT, limit);

		return jdbc.query(SELECT_BY_CATERORY_PAGING, params, rowMapper);
	}

	public Integer selectCount() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.EMPTY_MAP, Integer.class);
	}
	public Integer selectCount(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY, params, Integer.class);
	}
}
