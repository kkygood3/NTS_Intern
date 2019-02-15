/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductImageDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.ProductImageDto;

/**
 * 프로덕트 이미지 Dao
 * @author jinwoo.bae
 */
@Repository
public class ProductImageDao extends BasicDao<ProductImageDto> {
	public ProductImageDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(ProductImageDto.class);
	}

	/**
	 * 프로덕트 id, type에 해당하는 이미지 개수를 반환
	 */
	public int selectProductImageCount(int productId, ImageType type) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("type", type.getName());
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_IMAGE_COUNT_BY_PRODUCT_ID_AND_TYPE, params, Integer.class);
	}

	/**
	 * 프로덕트 id, type에 해당하는 이미지들을 반환
	 */
	public List<ProductImageDto> selectProductImagesByType(int productId, ImageType type, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("type", type.getName());
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_PRODUCT_IMAGES_BY_TYPE, params, rowMapper);
	}
}
