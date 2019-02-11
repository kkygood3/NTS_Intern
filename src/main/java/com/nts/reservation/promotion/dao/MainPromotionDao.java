/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.promotion.dao;

import static com.nts.reservation.promotion.dao.queries.MainPromotionQueries.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.promotion.dto.MainPromotion;

@Repository
public class MainPromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPromotion> rowMapper = BeanPropertyRowMapper.newInstance(MainPromotion.class);

	public MainPromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MainPromotion> selectPagingCategories() {
		return jdbc.query(SELECT_PROMOTION_PAGE, rowMapper);
	}
}
