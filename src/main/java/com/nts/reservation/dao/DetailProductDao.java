/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.DetailProductSqls.GET_AVERAGE_SCORE;
import static com.nts.reservation.dao.DetailProductSqls.GET_COMMENTS;
import static com.nts.reservation.dao.DetailProductSqls.GET_COMMENT_IMAGES;
import static com.nts.reservation.dao.DetailProductSqls.GET_DISPLAY_INFO;
import static com.nts.reservation.dao.DetailProductSqls.GET_DISPLAY_INFO_IMAGE;
import static com.nts.reservation.dao.DetailProductSqls.GET_PRODUCT_IMAGES;
import static com.nts.reservation.dao.DetailProductSqls.GET_PRODUCT_PRICES;

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
	private RowMapper<Comment> commentMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<DisplayInfo> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayImageMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private RowMapper<ProductImage> productImageMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public DetailProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public BigDecimal getAverageScore(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		BigDecimal avgScore = BigDecimal.valueOf(0);
		if (jdbc.queryForObject(GET_AVERAGE_SCORE, param, Double.class) != null) {
			avgScore = BigDecimal.valueOf(jdbc.queryForObject(GET_AVERAGE_SCORE, param, Double.class))
				.setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		return avgScore;
	}

	public List<Comment> getComments(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(GET_COMMENTS, param, commentMapper);
	}

	public List<CommentImage> getCommentImages(Integer commentId) {
		Map<String, Integer> param = Collections.singletonMap("commentId", commentId);
		return jdbc.query(GET_COMMENT_IMAGES, param, commentImageMapper);
	}

	public DisplayInfo getDisplayInfo(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(GET_DISPLAY_INFO, param, displayInfoMapper);
	}

	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(GET_DISPLAY_INFO_IMAGE, param, displayImageMapper);
	}

	public List<ProductImage> getProductImages(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(GET_PRODUCT_IMAGES, param, productImageMapper);
	}

	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(GET_PRODUCT_PRICES, param, productPriceMapper);
	}

}
