package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_COMMENTS_AVERAGE_SCORE;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_DISPLAY_INFO;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_DISPLAY_INFO_IMAGE;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_PRODUCT_IMAGES;
import static com.nts.reservation.dao.sqls.DetailDaoSqls.SELECT_DETAIL_PRODUCT_PRICES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.detail.DisplayInfo;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.product.ProductImage;
import com.nts.reservation.dto.product.ProductPrice;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@Repository
public class DetailDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public DisplayInfo selectDisplayInfo(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_DISPLAY_INFO, params,
			BeanPropertyRowMapper.newInstance(DisplayInfo.class));
	}

	public List<ProductImage> selectProductImages(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_PRODUCT_IMAGES, params,
			BeanPropertyRowMapper.newInstance(ProductImage.class));
	}

	public DisplayInfoImage selectDisplayInfoImage(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_DISPLAY_INFO_IMAGE, params,
			BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
	}

	public Double selectAverageScore(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_DETAIL_COMMENTS_AVERAGE_SCORE, params, Double.class);
	}

	public List<ProductPrice> selectProductPrices(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_PRODUCT_PRICES, params,
			BeanPropertyRowMapper.newInstance(ProductPrice.class));
	}
}
