/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.dao;

import static com.nts.reservation.productdisplay.dao.querys.ProductDisplayQuerys.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.common.exception.NotFoundDataException;
import com.nts.reservation.productdisplay.model.ProductDisplay;

@Repository
public class ProductDisplayDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<ProductDisplay> productDisplayMapper;

	@Autowired
	public ProductDisplayDao(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<ProductDisplay> productDisplayMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.productDisplayMapper = productDisplayMapper;
	}

	/**
	 * 특정 display의 정보 반환
	 */
	public ProductDisplay selectProductDisplay(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_PRODUCT_DISPLAY, param, productDisplayMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundDataException();
		}
	}

	/**
	 * 특정 display의 관련 이미지 url 목록 반환
	 */
	public List<String> selectProductDisplayImageUrlList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForList(SELECT_PRODUCT_DISPLAY_IMAGE_URL_LIST, param, String.class);
	}
}
