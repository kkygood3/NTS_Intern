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

import com.nts.constant.DaoQueries;
import com.nts.dto.Category;
import com.nts.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCount() {
		return jdbc.queryForObject(DaoQueries.PROMOTION_SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<Promotion> selectAll() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.query(DaoQueries.PROMOTION_SELECT_ALL, params, rowMapper);
	}
}
