/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.promotion;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.promotion.Promotion;

import static com.nts.sqls.promotion.PromotionSqls.*;

/**
 * @author 전연빈
 */
@Repository
public class PromotionRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	/**
	 * @desc promotion 가져오기
	 * @return list Promotion
	 */
	public List<Promotion> selectPromotions() {
		return namedParameterJdbcTemplate.query(SELECT_PROMOTIONS, rowMapper);
	}

}
