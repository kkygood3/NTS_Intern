package com.nts.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.constant.DaoQueries;
import com.nts.dto.PromotionApiView;

@Repository
public class PromotionApiViewDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionApiView> rowMapper = BeanPropertyRowMapper.newInstance(PromotionApiView.class);

	public PromotionApiViewDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PromotionApiView> selectAllPromotions() {
		return jdbc.query(DaoQueries.PROMOTION_API_SELECT_ALL, Collections.emptyMap(), rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(DaoQueries.PROMOTION_API_SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
