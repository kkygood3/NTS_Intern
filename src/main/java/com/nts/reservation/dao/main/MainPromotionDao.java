/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.main.MainPromotion;
import com.nts.reservation.queries.main.MainPromotionQueries;

/**
 * /api/promotions 요청에 대응
 */
@Repository
public class MainPromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MainPromotion> rowMapper = BeanPropertyRowMapper.newInstance(MainPromotion.class);

	public MainPromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * main 페이지를 로드할 때 필요한 promotion정보 조회
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<MainPromotion> selectPromotions(int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("pagingLimit", pagingLimit);
		return jdbc.query(MainPromotionQueries.SELECT_PROMOTION, params, rowMapper);
	}

	/**
	 * main 페이지를 로드할 때 필요한 promotion 갯수 조회
	 */
	public int selectCount() {
		Integer count = jdbc.queryForObject(MainPromotionQueries.SELECT_PROMOTION_COUNT, Collections.emptyMap(),
			Integer.class);
		if (count == null) {
			count = 0;
		}

		return count;
	}
}
