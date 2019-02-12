/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao.impl;

import static com.nts.reservation.promotion.dao.query.PromotionDaoQuerys.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.dto.Promotion;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class PromotionDaoImpl implements PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Promotion> selectPromotions() {
		return jdbc.query(SELECT_PROMOTION, rowMapper);
	}

	@Override
	public int selectPromotionsCount() {
		return jdbc.queryForObject(SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
	}

}
