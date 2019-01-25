/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nts.reservation.model.Category;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.Promotion;

@Configuration
public class JdbcConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public RowMapper<Product> productMapper() {
		return BeanPropertyRowMapper.newInstance(Product.class);
	}

	@Bean
	public RowMapper<Category> categeoryMapper() {
		return BeanPropertyRowMapper.newInstance(Category.class);
	}

	@Bean
	RowMapper<Promotion> promotionMapper() {
		return BeanPropertyRowMapper.newInstance(Promotion.class);
	}
}
