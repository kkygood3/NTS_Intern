package com.nts.reservation.dao;

import static com.nts.reservation.dao.ProductDaoSqls.SELECT_BY_ID;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PAGING_BY_CATEGORY;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PROMOTION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public List<Product> getPromotions() {
		return jdbc.query(SELECT_PROMOTION, rowMapper);
	}

	public List<Product> getProductsByCategory(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING_BY_CATEGORY, rowMapper, params);
	}

	public List<Product> getProductsById(Integer id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return jdbc.query(SELECT_BY_ID, rowMapper, params);
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
