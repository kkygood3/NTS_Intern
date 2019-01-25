/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.dto.ProductDto;
import static com.nts.layer.sqls.ProductSqls.*;

import java.util.Collections;

import javax.sql.DataSource;

/**
 * @author 전연빈
 */
@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductDao(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc product 전체 갯수 가져오기
	 * @return count ( product 전체 갯수)
	 */
	public int selectProductAllCount() {
		return namedParameterJdbcTemplate.queryForObject(COUNT_ALL_BY_CATEGORY, Collections.emptyMap(), Integer.class);
	}
}
