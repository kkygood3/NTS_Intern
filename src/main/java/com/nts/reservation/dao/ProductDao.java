package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_ALL;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_BY_CATEGORY;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * 전체 상품 수 셀렉트
 * @author 시윤
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
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
