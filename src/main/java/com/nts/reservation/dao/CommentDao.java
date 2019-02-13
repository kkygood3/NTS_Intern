/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.model.Comment;
import com.nts.reservation.model.CommentListInfo;
import static com.nts.reservation.dao.querys.CommentQuerys.*;

@Repository
public class CommentDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private RowMapper<Comment> commentMapper;

	@Autowired
	private RowMapper<CommentListInfo> commentListInfo;

	/**
	 * 특정 display의 전체 comment 목록 반환
	 */
	public List<Comment> getCommentList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL, param, getCommentListResultSetExtractor());
	}

	/**
	 * 특정 display의 갯수제한된 comment 목록 반환
	 */
	public List<Comment> getLimitedCommentList(int displayInfoId, int limitCount) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		param.put("limitCount", limitCount);

		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_LIMIT, param, getCommentListResultSetExtractor());
	}

	/**
	 * 특정 display의 comment 관련정보 반환 (평균점수, 총댓글 갯수)
	 */
	public CommentListInfo getCommentListInfo(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_INFO, param, commentListInfo);
	}

	/**
	 * db에서 얻어온 comment image url을 comment 객체 img url list에 저장, 그리고 comment들을  list로 반환하게 하는 ResultSetExtractor 객체를 생성
	 */
	private ResultSetExtractor<List<Comment>> getCommentListResultSetExtractor() {
		return new ResultSetExtractor<List<Comment>>() {
			@Override
			public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer, Comment> commentMap = new HashMap<>();
				while (rs.next()) {
					int commentId = rs.getInt("comment_id");

					if (!commentMap.containsKey(commentId)) {
						commentMap.put(commentId, commentMapper.mapRow(rs, rs.getRow()));
					}

					String commentImageUrl = rs.getString("comment_image_url");
					if (commentImageUrl != null) {
						commentMap.get(commentId).getCommentImageUrlList().add(commentImageUrl);
					}

				}
				return new ArrayList<>(commentMap.values());
			}
		};
	}
}
