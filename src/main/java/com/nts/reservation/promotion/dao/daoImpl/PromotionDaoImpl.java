/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao.daoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.dao.sqls.PromotionDaoSqls;
import com.nts.reservation.promotion.dto.Promotion;

@Repository
public class PromotionDaoImpl implements PromotionDao {

	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Promotion> selectAllPromotions() {
		return jdbc.query(PromotionDaoSqls.GET_PROMOTION, rowMapper);
	}
}
