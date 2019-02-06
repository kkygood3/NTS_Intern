package com.nts.reservation.service.impl;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DetailDao;
import com.nts.reservation.detail.dto.Comment;
import com.nts.reservation.detail.dto.CommentImage;
import com.nts.reservation.detail.dto.DisplayInfo;
import com.nts.reservation.detail.dto.DisplayInfoImage;
import com.nts.reservation.detail.dto.ProductImage;
import com.nts.reservation.detail.dto.ProductPrice;
import com.nts.reservation.service.DetailService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
@Service
@Transactional(readOnly = true)
public class DetailServiceImpl implements DetailService {

	@Autowired
	DetailDao DetailDao;

	@Override
	public DisplayInfo getDisplayInfo(Long displayInfoId) {
		return DetailDao.getDisplayInfo(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(Long displayInfoId) {
		return DetailDao.getProductImages(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(Long displayInfoId) {
		return DetailDao.getDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<Comment> getComments(Long displayInfoId) {
		return DetailDao.getComments(displayInfoId);
	}

	@Override
	public List<CommentImage> getCommentsImages(Long displayInfoId) {
		return DetailDao.getCommentsImages(displayInfoId);
	}

	@Override
	public double getAverageScore(Long displayInfoId) {
		return DetailDao.getAverageScore(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(Long displayInfoId) {
		return DetailDao.getProductPrices(displayInfoId);
	}

}
