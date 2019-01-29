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

/**
 * model class 를 sql query로 사용할 수 있게 변환하는 Bean 생성 설정 class
 * @author 임상현, life4lord93@nts-corp.com
 */
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
