package com.nts.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.dto.Product;

@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	private String SELECT_ALL = "SELECT * FROM product ORDER BY id DESC";
	private String SELECT_BY_ID = "SELECT * FROM product where id = :id";
	private String SELECT_PAGING = "SELECT * FROM product ORDER BY id DESC limit :start, :limit";
	private String SELECT_COUNT = "SELECT count(*) FROM product";
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAllProducts() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
	
	public Product selectById(long id) {
		Map<String, Long> params = new HashMap<>();
		params.put("id", id);
		return jdbc.query(SELECT_BY_ID, params, rowMapper).get(0);
	}
	
	public List<Product> selectPagingProducts(int start,int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING, params, rowMapper);
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
	
}
