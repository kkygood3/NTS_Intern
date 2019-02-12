/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CommentDaoSqls.SELECT_COMMENT_AVG_SCORE_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.dao.sql.CommentDaoSqls.SELECT_COMMENT_BY_DISPLAY_INFO_ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CommentDto;

/**
 * @author 육성렬
 */
@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);

	public CommentDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc displayInfoId에 해당하는 모든 Comment들을 쿼리한다.
	 * @param displayInfoId
	 * @return List<CommentDto>
	 */
	public List<CommentDto> selectComments(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.query(SELECT_COMMENT_BY_DISPLAY_INFO_ID, map, rowMapper);
	}

	/**
	 * @desc 모든 프로덕트의 갯수를 DB에 요청한다.
	 * @return Integer 프로덕트의 갯수
	 */
	public float selectCommentAvgScore(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		Float averageScore = jdbc.queryForObject(SELECT_COMMENT_AVG_SCORE_BY_DISPLAY_INFO_ID, map, Float.class);
		if (averageScore == null) {
			return 0;
		} else {
			return averageScore;
		}
	}
}
