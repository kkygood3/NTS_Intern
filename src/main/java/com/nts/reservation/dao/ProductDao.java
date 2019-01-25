package com.nts.reservation.dao;

import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PAGING;
import static com.nts.reservation.dao.ProductDaoSqls.SELECT_PROMOTION;

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

import com.nts.reservation.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("product")
			.usingGeneratedKeyColumns("id");
	}

	public List<Product> getPromotion() {
		return jdbc.query(SELECT_PROMOTION, Collections.emptyMap(), rowMapper);
	}

	public List<Product> selectAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING, params, rowMapper);
	}
	//
	//	public Long insert(Product guestbook) {
	//		SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
	//		return insertAction.executeAndReturnKey(params).longValue();
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
