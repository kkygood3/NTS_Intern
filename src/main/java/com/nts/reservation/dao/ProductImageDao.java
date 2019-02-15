/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductImageDaoSqls.*;

import java.util.Collections;
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
	 * 프로덕트 ID에 해당하는 이미지 개수를 count
	 */
	public int selectProductImageCount(int productId) {
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_IMAGE_COUNT, Collections.singletonMap("productId", productId),
			Integer.class);
	}

	/**
	 * 해당 상품 id의 이미지들을 가져옵니다.
	 */
	public List<ProductImageDto> selectProductImages(int productId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_PRODUCT_IMAGES, params, rowMapper);
	}

	/**
	 * id, type에 해당하는 이미지들을 가져옵니다.
	 */
	public List<ProductImageDto> selectProductImagesByType(int productId, ImageType type, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("type", type.getName());
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_PRODUCT_IMAGES_BY_TYPE, params, rowMapper);
	}
}
