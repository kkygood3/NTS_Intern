/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CommentImageDaoSqls.SELECT_COMMENT_IMAGE_BY_ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CommentImageDto;

/**
 * @author 육성렬
 */
@Repository
public class CommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImageDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);

	public CommentImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc reservationUserCommentId를 기반으로 해당하는 CommentImage들을 쿼리한다.
	 * @param reservationUserCommentId
	 * @return List<CommentImageDto> 
	 */
	public List<CommentImageDto> selectCommentImages(Long commentId) {
		Map<String, Object> map = Collections.singletonMap("id", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGE_BY_ID, map, rowMapper);
	}
}
