/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.nts.reservation.model.Category;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.Promotion;

@Configuration
public class MapperConfig {

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
