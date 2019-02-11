/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.model.Comment;
import com.nts.reservation.model.CommentMetaData;
import static com.nts.reservation.dao.querys.CommentQuerys.*;

@Repository
public class CommentDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Comment> commentMapper;

	@Autowired
	private RowMapper<CommentMetaData> commentMetaDataMapper;

	/**
	 * 특정 display의 전체 comment 목록 반환
	 */
	public List<Comment> getCommentList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL, param, commentMapper);
	}

	/**
	 * 특정 display의 갯수제한된 comment 목록 반환
	 */
	public List<Comment> getLimitedCommentList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_LIMIT, param, commentMapper);
	}

	/**
	 * 특정 display의 comment 관련정보 반환 (평균점수, 총댓글 갯수)
	 */
	public CommentMetaData getCommentMetaData(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_DISPLAY_COMMENT_META_DATA, param, commentMetaDataMapper);
	}

}
