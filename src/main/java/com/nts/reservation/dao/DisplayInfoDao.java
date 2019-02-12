/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DisplayInfoDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.CommentImageDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;

/**
 * @author 육성렬
 */
@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoDto> displayRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);
	private RowMapper<CommentDto> commentRowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
	private RowMapper<CommentImageDto> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);
	private RowMapper<DisplayInfoImageDto> displayImageRowMapper = BeanPropertyRowMapper
		.newInstance(DisplayInfoImageDto.class);
	private RowMapper<ProductImageDto> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);
	private RowMapper<ProductPriceDto> priceRowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDto.class);

	public DisplayInfoDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 아이디에 해당하는 DisplayInfo Row를 하나 쿼리한다.
	 * @param displayInfoId
	 * @return DisplayInfoDto
	 */
	public DisplayInfoDto selectDisplayInfo(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_BY_ID, map, displayRowMapper);
	}

	/**
	 * @desc displayInfoId에 해당하는 모든 Comment들을 쿼리한다.
	 * @param displayInfoId
	 * @return List<CommentDto>
	 */
	public List<CommentDto> selectComments(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.query(SELECT_COMMENT_BY_DISPLAY_INFO_ID, map, commentRowMapper);
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

	/**
	 * @desc reservationUserCommentId를 기반으로 해당하는 CommentImage들을 쿼리한다.
	 * @param reservationUserCommentId
	 * @return List<CommentImageDto> 
	 */
	public List<CommentImageDto> selectCommentImages(Long commentId) {
		Map<String, Object> map = Collections.singletonMap("id", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGE_BY_ID, map, commentImageRowMapper);
	}

	/**
	 * @desc 아이디 값에 해당하는 DisplayInfoImage 행하나를 쿼리한다.
	 * @param id
	 * @return DisplayInfoImageDto
	 */
	public DisplayInfoImageDto selectDisplayInfoImage(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_ID, map, displayImageRowMapper);
	}

	/**
	 * @desc productId에 해당하는 ProductImage들을 쿼리한다.
	 * @param productId
	 * @return List<ProductImageDto>
	 */
	public List<ProductImageDto> selectProductImages(Long productId) {
		Map<String, Object> map = Collections.singletonMap("id", productId);
		return jdbc.query(SELECT_PRODUCT_IMAGE_BY_ID, map, productImageRowMapper);
	}

	/**
	 * @desc productId에 해당하는 ProductPrice들을 쿼리한다.
	 * @param productId
	 * @return List<ProductPriceDto>
	 */
	public List<ProductPriceDto> selectProductPrices(Long productId) {
		Map<String, Object> map = Collections.singletonMap("id", productId);
		return jdbc.query(SELECT_PRODUCT_PRICE_BY_ID, map, priceRowMapper);
	}
}
