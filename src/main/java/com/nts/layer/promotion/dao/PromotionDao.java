/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.promotion.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.layer.promotion.dto.Promotion;
import static com.nts.layer.promotion.sqls.PromotionSqls.*;

/**
 * @author 전연빈
 */
@Repository
public class PromotionDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	/**
	 * @desc 카테고리들 가져오기
	 * @return categories
	 */
	public List<Promotion> selectCategories() {
		return namedParameterJdbcTemplate.query(SELECT_PROMOTIONS, rowMapper);
	}
	
}
