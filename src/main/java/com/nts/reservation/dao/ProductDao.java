package com.nts.reservation.dao;

import static com.nts.reservation.dao.ProductDaoSqls.SELECT_ALL_PRODUCTS;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_ALL_PRODUCTS_BY_CATEGORY;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PRODUCTS_COUNT;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PROMOTION;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;

@Repository
public class ProductDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public List<Promotion> getPromotions() {
		return jdbc.query(SELECT_PROMOTION, BeanPropertyRowMapper.newInstance(Promotion.class));
	}

	public List<Product> getProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCTS, params, rowMapper);

	}

	public List<Product> getProductsByCategory(int start, int category, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("category_id", category);
		return jdbc.query(SELECT_ALL_PRODUCTS_BY_CATEGORY, params, rowMapper);

	}

	public Long getProductsCount() {

		return jdbc.queryForObject(SELECT_PRODUCTS_COUNT, Collections.emptyMap(), Long.class);
	}

	//	public Long insert(Product product) {
	//		SqlParameterSource params = new BeanPropertySqlParameterSource(product);
	//		return insertAction.executeAndReturnKey(params).longValue();
	//	}

	//	public int getCount() {
	//		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY, Collections.emptyMap(), Integer.class);
	//	}

	//	public int getPromotionCount() {
	//		return jdbc.queryForObject(SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
	//	}
	//
	//	public int deleteById(Long id) {
	//		Map<String, ?> params = Collections.singletonMap("id", id);
	//		return jdbc.update(DELETE_BY_ID, params);
	//	}
	//
	//	public int selectCount() {
	//		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	//	}
}
