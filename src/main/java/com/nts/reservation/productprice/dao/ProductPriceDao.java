/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.dao;

import static com.nts.reservation.productprice.dao.querys.ProductPriceQuerys.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.productprice.model.ProductPrice;

@Repository
public class ProductPriceDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<ProductPrice> productPriceMapper;

	@Autowired
	public ProductPriceDao(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<ProductPrice> productPriceMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.productPriceMapper = productPriceMapper;
	}

	/**
	 * 상품의 가격정보 조회
	 */
	public List<ProductPrice> selectProductPriceListByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_PRICE_LIST, param, productPriceMapper);
	}

}
