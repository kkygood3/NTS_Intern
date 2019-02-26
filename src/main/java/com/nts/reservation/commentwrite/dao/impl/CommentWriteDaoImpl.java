/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.dao.impl;

import static com.nts.reservation.commentwrite.dao.queries.CommentWriteQueries.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

@Repository
public class CommentWriteDaoImpl implements CommentWriteDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentWriteDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int insertComment(CommentWriteRequest commentWriteRequest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", commentWriteRequest.getProductId());
		params.addValue("reservationInfoId", commentWriteRequest.getReservationInfoId());
		params.addValue("comment", commentWriteRequest.getComment());
		params.addValue("score", commentWriteRequest.getScore());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_COMMENT, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public int insertFileInfo(CommentWriteRequest commentWriteRequest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("fileName", commentWriteRequest.getFileName());
		params.addValue("saveFileName", commentWriteRequest.getSaveFileName());
		params.addValue("contentType", commentWriteRequest.getContentType());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_FILE_INFO, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public int insertCommentImage(CommentWriteRequest commentWriteRequest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationInfoId", commentWriteRequest.getReservationInfoId());
		params.addValue("reservationUserCommentId", commentWriteRequest.getReservationUserCommentId());
		params.addValue("fileId", commentWriteRequest.getFileInfoId());
		return jdbc.update(INSERT_COMMENT_IMAGE, params);
	}


}
