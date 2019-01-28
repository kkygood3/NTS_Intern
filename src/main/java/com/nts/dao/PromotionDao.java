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

import com.nts.dto.Category;
import com.nts.dto.Promotion;

@Repository
public class PromotionDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	private final String DaoTableName = "promotion";

	private String SELECT_ALL = "SELECT * FROM promotion";
	private String SELECT_COUNT = "SELECT count(*) FROM promotion";

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName(DaoTableName)
			.usingGeneratedKeyColumns("id");
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<Promotion> selectAll() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
}
