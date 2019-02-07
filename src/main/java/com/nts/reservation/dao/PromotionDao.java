/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sqls.PromotionDaoSqls;
import com.nts.reservation.dto.Promotion;

@Repository
public class PromotionDao {

	private NamedParameterJdbcTemplate jdbc;

	static final int ALL_CATEGORIES = 0;

	// ResultSet 에서 값을 추출하여 원하는 객체로 변환
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * Promotion를 가져옴
	 * @author yongjoon.Park
	 * @return
	 */
	public List<Promotion> getPromotions() {
		return jdbc.query(PromotionDaoSqls.GET_PROMOTION, rowMapper);
	}
}
