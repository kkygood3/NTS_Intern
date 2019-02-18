/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_AVERAGE_SCORE;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_COMMENTS;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_COMMENT_IMAGES;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_DISPLAY_INFO;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_DISPLAY_INFO_IMAGE;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_PRODUCT_IMAGES;
import static com.nts.reservation.dao.sqls.DetailProductSqls.SELECT_PRODUCT_PRICES;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
* @author  : 이승수
*/
@Repository
public class DetailProductDao {
	private NamedParameterJdbcTemplate jdbc;

	public DetailProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public BigDecimal selectAverageScore(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_AVERAGE_SCORE, param, BigDecimal.class);
	}

	public List<Comment> selectComments(Integer displayInfoId) {
		RowMapper<Comment> commentMapper = BeanPropertyRowMapper.newInstance(Comment.class);
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENTS, param, commentMapper);
	}

	public List<CommentImage> selectCommentImages(Integer commentId) {
		RowMapper<CommentImage> commentImageMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
		Map<String, Integer> param = Collections.singletonMap("commentId", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGES, param, commentImageMapper);
	}

	public DisplayInfo selectDisplayInfo(Integer displayInfoId) {
		RowMapper<DisplayInfo> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, param, displayInfoMapper);
	}

	public DisplayInfoImage selectDisplayInfoImage(Integer displayInfoId) {
		RowMapper<DisplayInfoImage> displayImageMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, param, displayImageMapper);
	}

	public List<ProductImage> getProductImages(Integer displayInfoId) {
		RowMapper<ProductImage> productImageMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGES, param, productImageMapper);
	}

	public List<ProductPrice> selectProductPrices(Integer displayInfoId) {
		RowMapper<ProductPrice> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICES, param, productPriceMapper);
	}

}