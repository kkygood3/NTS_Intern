/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.model.ProductDisplay;
import static com.nts.reservation.dao.querys.ProductDisplayQuerys.*;

@Repository
public class ProductDisplayDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<ProductDisplay> productDisplayMapper;

	public ProductDisplay getProductDisplay(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_PRODUCT_DISPLAY, param, productDisplayMapper);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ProductDisplay();
		}
	}

	public List<String> getProductDisplayImageUrlList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForList(SELECT_PRODUCT_DISPLAY_IMAGE_URL_LIST, param, String.class);
	}
}
