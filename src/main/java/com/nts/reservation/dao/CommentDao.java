/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.CommentSqls.INSERT_COMMENT;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment;

/**
* @author  : 이승수
*/
@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertComment(Comment comment) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_COMMENT, new BeanPropertySqlParameterSource(comment), keyHolder);
		return keyHolder.getKey().intValue();
	}
}