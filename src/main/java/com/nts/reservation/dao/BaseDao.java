package com.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Dao에서 기본적으로 필요한 NamedParameterJdbcTemplate 변수를 가지는 클래스
 * @author 시윤
 *
 */
public class BaseDao {

	private NamedParameterJdbcTemplate jdbc;
	BaseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	NamedParameterJdbcTemplate getJDBC() {
		return jdbc;
	}
}
