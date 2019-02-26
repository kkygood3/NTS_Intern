/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.nts.reservation.category.model.Category;
import com.nts.reservation.comment.model.Comment;
import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.file.model.FileInfo;
import com.nts.reservation.product.model.Product;
import com.nts.reservation.productdisplay.model.ProductDisplay;
import com.nts.reservation.productprice.model.ProductPrice;
import com.nts.reservation.promotion.model.Promotion;
import com.nts.reservation.reservation.model.ReservationHistory;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * model class 를 sql query로 사용할 수 있게 변환하는 Bean 생성 설정 class
 */
@Configuration
public class MapperConfig {

	@Bean
	public RowMapper<Product> productMapper() {
		return BeanPropertyRowMapper.newInstance(Product.class);
	}

	@Bean
	public RowMapper<Category> categeoryMapper() {
		return BeanPropertyRowMapper.newInstance(Category.class);
	}

	@Bean
	public RowMapper<Promotion> promotionMapper() {
		return BeanPropertyRowMapper.newInstance(Promotion.class);
	}

	@Bean
	public RowMapper<ProductDisplay> productDisplayMapper() {
		return BeanPropertyRowMapper.newInstance(ProductDisplay.class);
	}

	@Bean
	public RowMapper<Comment> commentMapper() {
		return BeanPropertyRowMapper.newInstance(Comment.class);
	}

	@Bean
	public RowMapper<CommentListInfo> commentListInfoMapper() {
		return BeanPropertyRowMapper.newInstance(CommentListInfo.class);
	}

	@Bean
	public RowMapper<ProductPrice> productPriceMapper() {
		return BeanPropertyRowMapper.newInstance(ProductPrice.class);
	}

	@Bean
	public RowMapper<ReservationHistory> reservationHistoryMapper() {
		return BeanPropertyRowMapper.newInstance(ReservationHistory.class);
	}

	@Bean
	public RowMapper<FileInfo> fileInfoMapper() {
		return BeanPropertyRowMapper.newInstance(FileInfo.class);
	}

}
