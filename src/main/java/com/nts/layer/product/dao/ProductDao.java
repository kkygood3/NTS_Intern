/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.dao;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.layer.product.dto.Product;
import static com.nts.layer.product.sqls.ProductSqls.*;

/**
 * @author 전연빈
 */
@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	/**
	 * @desc product 전체 갯수 가져오기
	 * @return count ( product 전체 갯수)
	 */
	public int selectProductAllCount() {
		return namedParameterJdbcTemplate.queryForObject(COUNT_ALL_BY_CATEGORY, Collections.emptyMap(), Integer.class);
	}
}
