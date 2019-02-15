/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dao.impl;

import static com.nts.reservation.comment.dao.queries.CommentQueries.*;

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

@Repository
public class CommentDaoImpl implements CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImage> rowMapperCommentImage = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<Comment> rowMapperComment = BeanPropertyRowMapper.newInstance(Comment.class);

	public CommentDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<CommentImage> selectCommentImages(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGES, params, rowMapperCommentImage);
	}

	@Override
	public List<Comment> selectCommentByLimit(int displayInfoId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("limit", limit);
		return jdbc.query(SELECT_COMMENT_BY_LIMIT, params, rowMapperComment);
	}

	@Override
	public List<Comment> selectComment(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENT, params, rowMapperComment);
	}

}
