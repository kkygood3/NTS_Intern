/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final int LIMIT = 4;

	/**
	 * @desc 카테고리별 데이터 가져오기
	 * @param categoryId
	 * @param start
	 * @return list(Product)
	 */
	public List<Product> selectProductsByCategory(String categoryId, int start) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("categoryId","%"+categoryId+"%");
		params.put("start", start*LIMIT);
		params.put("limit",LIMIT);
		
		return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_CATEGORY, params, rowMapper);
	}
	
	/**
	 * @desc 카테고리 별 카운트 갯수
	 * @param categoryId
	 * @return totalCount
	 */
	public int selectCountByCategory(String categoryId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("categoryId","%"+categoryId+"%");
		
		return namedParameterJdbcTemplate.queryForObject(SELECT_COUNT_BY_CATEGORY,params,Integer.class);
	}
}
 