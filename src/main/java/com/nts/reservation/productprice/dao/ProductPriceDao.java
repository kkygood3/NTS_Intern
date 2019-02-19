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

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<ProductPrice> productPriceMapper;

	public List<ProductPrice> selectProductPriceList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_PRICE_LIST, param, productPriceMapper);
	}

}
