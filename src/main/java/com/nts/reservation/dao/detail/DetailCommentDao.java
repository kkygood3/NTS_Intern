/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.DetailCommentSqls;
import com.nts.reservation.dto.detail.DetailComment;

/**
 * /api/products/{displayInfoId} 요청에 대응
 */
@Repository
public class DetailCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailComment> rowMapper = BeanPropertyRowMapper.newInstance(DetailComment.class);

	public DetailCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * detail 페이지를 로드할 때 Comment 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<DetailComment> selectCommentPageByDisplayInfoId(int displayInfoId, int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("pagingLimit", pagingLimit);
		List<DetailComment> detailList = jdbc.query(DetailCommentSqls.SELECT_DETAIL_COMMENT, params, rowMapper);

		return detailList;
	}
}
