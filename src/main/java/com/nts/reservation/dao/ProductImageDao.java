/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductImageDaoSqls.SELECT_PRODUCT_IMAGE_BY_ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductImageDto;

/**
 * @author 육성렬
 */
@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImageDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);

	public ProductImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc productId에 해당하는 ProductImage들을 쿼리한다.
	 * @param productId
	 * @return List<ProductImageDto>
	 */
	public List<ProductImageDto> selectProductImages(Long productId) {
		Map<String, Object> map = Collections.singletonMap("id", productId);
		return jdbc.query(SELECT_PRODUCT_IMAGE_BY_ID, map, rowMapper);
	}
}
