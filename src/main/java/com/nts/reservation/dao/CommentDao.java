/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.CommentSqls.INSERT_COMMENT;
import static com.nts.reservation.dao.sqls.CommentSqls.INSERT_COMMENT_IMAGE;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;

/**
* @author  : 이승수
*/
@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getIdAfterInsertComment(Comment comment) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_COMMENT, new BeanPropertySqlParameterSource(comment), keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void insertCommentImage(CommentImage commentImage) {
		jdbc.update(INSERT_COMMENT_IMAGE, new BeanPropertySqlParameterSource(commentImage));
	}
}