/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dao;

import static com.nts.resevation.dao.CommentDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nts.resevation.dto.CommentDto;
import com.nts.resevation.dto.CommentImageDto;

/**
 * 상품평 정보를 가져오는 Dao 클래스
 * @author jinwoo.bae
 */
@Repository
public class CommentDao extends BasicDao<CommentDto> {
	public CommentDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(CommentDto.class);
	}

	/**
	 * 해당 productId의 상품평을 start번재뿌터 limit만큼 가져옵니다.
	 */
	public List<CommentDto> selectComments(int productId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("limit", limit);
		List<CommentDto> comments = jdbcTemplate.query(SELECT_COMMENTS, params, rowMapper);
		for (CommentDto comment : comments) {
			comment.setCommentImage(selectCommentImage(comment.getCommentId()));
		}
		return comments;
	}

	/**
	 * 해당 commentId의 상품 이미지를 가져옵니다. 등록된 이미지가 없으면 null 반환
	 */
	public CommentImageDto selectCommentImage(int commentId) {
		try {
			return jdbcTemplate.queryForObject(SELECT_COMMENT_IMAGE, Collections.singletonMap("commentId", commentId),
				BeanPropertyRowMapper.newInstance(CommentImageDto.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 해당 productId의 상품평 평균점수를 가져옵니다.
	 */
	public double selectCommentAvgScore(int productId) {
		return jdbcTemplate.queryForObject(SELECT_COMMENT_AVG_SCORE, Collections.singletonMap("productId", productId),
			Double.class);
	}

	/**
	 * 해당 프로덕트에 등록된 상품평 개수를 리턴 
	 */
	public int selectCommentCount(int productId) {
		return jdbcTemplate.queryForObject(SELECT_COMMENT_COUNT, Collections.singletonMap("productId", productId),
			Integer.class);
	}
}
