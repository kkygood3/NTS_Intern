package com.nts.reservation.dao;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS_AVERAGE_SCORE;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS_IMAGES;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS_PRODUCT_PRICES;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_DISPLAY_INFO;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_DISPLAY_INFO_IMAGE;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_PRODUCT_IMAGES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.detail.dto.Comment;
import com.nts.reservation.detail.dto.CommentImage;
import com.nts.reservation.detail.dto.DisplayInfo;
import com.nts.reservation.detail.dto.DisplayInfoImage;
import com.nts.reservation.detail.dto.ProductImage;
import com.nts.reservation.detail.dto.ProductPrice;

@Repository
public class DetailDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public DisplayInfo getDisplayInfo(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_DISPLAY_INFO, params,
			BeanPropertyRowMapper.newInstance(DisplayInfo.class));
	}

	public List<ProductImage> getProductImages(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_PRODUCT_IMAGES, params,
			BeanPropertyRowMapper.newInstance(ProductImage.class));
	}

	public DisplayInfoImage getDisplayInfoImage(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_DISPLAY_INFO_IMAGE, params,
			BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
	}

	public List<Comment> getComments(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_COMMENTS, params,
			BeanPropertyRowMapper.newInstance(Comment.class));
	}

	public List<CommentImage> getCommentsImages(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_COMMENTS_IMAGES, params,
			BeanPropertyRowMapper.newInstance(CommentImage.class));
	}

	public double getAverageScore(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_COMMENTS_AVERAGE_SCORE, params, double.class);
	}

	public List<ProductPrice> getProductPrices(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_COMMENTS_PRODUCT_PRICES, params,
			BeanPropertyRowMapper.newInstance(ProductPrice.class));
	}

	public List<CommentImage> getCommentsImagesByCommentId(Long CommentId) {
		Map<String, Long> params = new HashMap<>();
		params.put("commentId", CommentId);
		return jdbc.query(SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID, params,
			BeanPropertyRowMapper.newInstance(CommentImage.class));

	}

}
