/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.dao.impl;

import static com.nts.reservation.commentwrite.dao.queries.CommentWriteQueries.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.ReviewWriteRequest;

@Repository
public class CommentWriteDaoImpl implements CommentWriteDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentWriteDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int insertComment(ReviewWriteRequest reviewWriteRequest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", reviewWriteRequest.getProductId());
		params.addValue("reservationInfoId", reviewWriteRequest.getReservationInfoId());
		params.addValue("comment", reviewWriteRequest.getComment());
		params.addValue("score", reviewWriteRequest.getScore());
		return jdbc.update(INSERT_COMMENT, params);
	}

}
