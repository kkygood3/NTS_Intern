package com.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class BaseDao {

	private NamedParameterJdbcTemplate jdbc;
	BaseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	NamedParameterJdbcTemplate getJDBC() {
		return jdbc;
	}
}
