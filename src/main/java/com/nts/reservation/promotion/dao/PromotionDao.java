/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao;

import static com.nts.reservation.promotion.dao.querys.PromotionQuerys.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.promotion.model.Promotion;

@Repository
public class PromotionDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Promotion> promotionMapper;

	/**
	 * promotion 목록을 조회하여 List 객체로 반환
	 */
	public List<Promotion> getPromotionList() {
		return jdbcTemplate.query(SELECT_PROMOTION_LIST, promotionMapper);
	}

}
