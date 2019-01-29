package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_ALL_PAGING;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_BY_CATERORY_PAGING;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT;

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
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_ALL_PAGING, params, rowMapper);
	}

	public List<Product> selectByCategory(Category category, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		System.out.println("category : " + category);
		params.put("categoryId", category.getId());
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_BY_CATERORY_PAGING, params, rowMapper);
	}

	public Integer selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.EMPTY_MAP, Integer.class);
	}
}
