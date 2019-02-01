/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductPriceDaoSqls.SELECT_PRODUCT_PRICE_BY_ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductPriceDto;

/**
 * @author 육성렬
 */
@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPriceDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDto.class);

	public ProductPriceDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc productId에 해당하는 ProductPrice들을 쿼리한다.
	 * @param productId
	 * @return List<ProductPriceDto>
	 */
	public List<ProductPriceDto> selectProductPrices(Long productId) {
		Map<String, Object> map = Collections.singletonMap("id", productId);
		return jdbc.query(SELECT_PRODUCT_PRICE_BY_ID, map, rowMapper);
	}
}
