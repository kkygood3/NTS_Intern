/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.HomeCategorySqls;
import com.nts.reservation.dto.main.HomeCategory;

/**
 * /api/categories 요청에 대응
 */
@Repository
public class HomeCategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<HomeCategory> rowMapper = BeanPropertyRowMapper.newInstance(HomeCategory.class);

	public HomeCategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * main 페이지를 로드할 때 필요한 category정보 조회
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<HomeCategory> selectCategories(int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("pagingLimit", pagingLimit);
		return jdbc.query(HomeCategorySqls.SELECT_CATEGORIES, params, rowMapper);
	}
}
