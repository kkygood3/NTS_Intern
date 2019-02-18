/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dao.sqls.CommentDaoSqls;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

@Repository
public class CommentDaoImpl implements CommentDao {

	private NamedParameterJdbcTemplate jdbc;

	public CommentDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	@Override
	public List<Comment> selectComments(int displayInfoId, int limit) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		param.put("limit", limit);
		return jdbc.query(CommentDaoSqls.GET_LIMIT_COMMENT, param, commentRowMapper);
	}

	@Override
	public List<CommentImage> selectCommentImage(int commentId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("commentId", commentId);
		return jdbc.query(CommentDaoSqls.GET_COMMENT_IMAGES_BY_COMMENT_ID, param, commentImageRowMapper);
	}

	@Override
	public double selectCommentAvgScore(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(CommentDaoSqls.GET_COMMENT_AVG_SCORE, param, Double.class);
	}
}
