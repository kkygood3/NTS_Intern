/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_COUNT_PROMOTION;
import static com.nts.reservation.dao.sqls.ProductSqls.SELECT_PROMOTION_PRODUCTS;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;

/**
* @author  : 이승수
*/
@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectPromotions() {
		return jdbc.query(SELECT_PROMOTION_PRODUCTS, rowMapper);
	}

	public int selectCountPromotions() {
		return jdbc.queryForObject(SELECT_COUNT_PROMOTION, Collections.emptyMap(), Integer.class);
	}
}