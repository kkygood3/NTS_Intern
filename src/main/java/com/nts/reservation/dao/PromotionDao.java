/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.querys.PromotionQuerys;
import com.nts.reservation.model.Promotion;

@Repository
public class PromotionDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Promotion> promotionMapper;

	public List<Promotion> getPromotionList() {
		return jdbcTemplate.query(PromotionQuerys.SELECT_PROMOTION_LIST, Collections.EMPTY_MAP, promotionMapper);
	}

}
