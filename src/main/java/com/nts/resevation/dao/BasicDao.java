/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 기본 Dao 클래스
 * @author jinwoo.bae
 */
@Repository
public class BasicDao<T> {
	protected NamedParameterJdbcTemplate jdbcTemplate;
	protected RowMapper<T> rowMapper;

	public BasicDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void setRowMapper(Class<T> cls) {
		this.rowMapper = BeanPropertyRowMapper.newInstance(cls);
	}
}
