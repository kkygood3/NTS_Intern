/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

import static com.nts.reservationservice.dao.ProductImageDaoSqls.SELECT_PRODUCT_IMAGE_BY_PRODUCT_ID_AND_TYPE;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservationservice.dto.ProductImageDto;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImageDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);

	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ProductImageDto selectProductImageByProductIdAndType(Long productId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", productId);
		params.put("type", type);

		return jdbc.queryForObject(SELECT_PRODUCT_IMAGE_BY_PRODUCT_ID_AND_TYPE, params, rowMapper);
	}
}
