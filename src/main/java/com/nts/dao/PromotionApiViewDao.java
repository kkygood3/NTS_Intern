package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.PromotionApiView;

@Repository
public class PromotionApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionApiView> rowMapper = BeanPropertyRowMapper.newInstance(PromotionApiView.class);

	private String SELECT_ALL = "SELECT * FROM promotion_api_view";
	private String SELECT_COUNT = "SELECT count(*) FROM promotion_api_view";

	public PromotionApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PromotionApiView> selectAllPromotions() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
