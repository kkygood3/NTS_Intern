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

import com.nts.reservation.dao.sql.HomeResponseSqls;
import com.nts.reservation.dto.main.HomeProduct;

/**
 * /api/products 요청에 대응
 */
@Repository
public class HomeProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<HomeProduct> rowMapper = BeanPropertyRowMapper.newInstance(HomeProduct.class);

	public HomeProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * main 페이지를 로드할 때 필요한 product정보 조회. 카테고리 구분 없음
	 * @param start - 한 페이지에 출력할 item 개수
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<HomeProduct> selectPagingProducts(int start, int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("pagingLimit", pagingLimit);
		return jdbc.query(HomeResponseSqls.SELECT_PRODUCT_PAGE, params, rowMapper);
	}

	/**
	 * main 페이지를 로드할 때 필요한 product정보 조회
	 * @param categoryId - 해당 카테고리에 속하는 product로 한정
	 * @param start - 한 페이지에 출력할 item 개수
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<HomeProduct> selectPagingProductsByCategory(int categoryId, int start, int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("pagingLimit", pagingLimit);
		return jdbc.query(HomeResponseSqls.SELECT_PRODUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	/**
	 * main 페이지를 로드할 때 필요한 product 갯수 조회
	 * @param categoryId - 해당 카테고리에 속하는 product로 한정
	 */
	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(HomeResponseSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	/**
	 * main 페이지를 로드할 때 필요한 product 갯수 조회. 카테고리 구분 없음
	 */
	public int selectCount() {
		return jdbc.queryForObject(HomeResponseSqls.SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
