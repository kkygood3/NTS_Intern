package com.nts.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.ProductApiView;

@Repository
public class ProductApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductApiView> rowMapper = BeanPropertyRowMapper.newInstance(ProductApiView.class);
	
	private String SELECT_ALL = "SELECT * FROM product_api_view ORDER BY product_id DESC limit :start, :limit";
	private String SELECT_CATEGORY = "SELECT * FROM product_api_view where category_id = :category_id ORDER BY product_id DESC limit :start, :limit";
	private String SELECT_COUNT = "SELECT count(*) FROM product_api_view where category_id = :category_id";
	private String SELECT_COUNT_ALL = "SELECT count(*) FROM product_api_view";

	public ProductApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductApiView> selectAllPagingProducts(long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
	
	public List<ProductApiView> selectPagingProducts(long categoryId, long start, long limit) {
		Map<String, Long> params = new HashMap<>();
		params.put("category_id", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_CATEGORY, params, rowMapper);
	}

	public int selectCount(long categoryId) {
		Map<String, Long> params = new HashMap<>();
		params.put("category_id", categoryId);
		return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
	}
	
	public int selectCountAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
}
