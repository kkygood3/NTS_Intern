/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.impl;

import static com.nts.reservation.comment.dao.query.CommentDaoQuerys.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.DetailComment;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class CommentDaoImpl implements CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<DetailComment> detailCommentRowMapper = BeanPropertyRowMapper.newInstance(DetailComment.class);

	public CommentDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Comment> selectAllComment(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_ALL_COMMENT, param, commentRowMapper);
	}

	@Override
	public List<Comment> selectLimitComment(int displayInfoId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_LIMIT_COMMENT, params, commentRowMapper);
	}

	@Override
	public List<CommentImage> selectCommentImage(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGE, params, commentImageRowMapper);
	}

	@Override
	public List<DetailComment> selectDetailComment(int displayInfoId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_DETAIL_COMMENT, params, detailCommentRowMapper);
	}
}
