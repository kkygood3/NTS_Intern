/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * DB에 접근하는 jdbc template Bean 생성 설정 class
 */
@Configuration
public class JdbcConfig {

	private final DataSource dataSource;

	@Autowired
	public JdbcConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
