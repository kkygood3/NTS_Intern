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
import com.nts.reservation.dto.common.DisplayInfo;
import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.dto.detail.CommentImage;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.detail.ProductImage;
import com.nts.reservation.dto.detail.ProductPrice;
import com.nts.reservation.service.DetailService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
@Service
@Transactional(readOnly = true)
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailDao DetailDao;

	@Override
	public DisplayInfo getDisplayInfo(Long displayInfoId) {
		return DetailDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(Long displayInfoId) {
		return DetailDao.selectProductImages(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(Long displayInfoId) {
		return DetailDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<Comment> getComments(Long displayInfoId) {
		List<Comment> comments = DetailDao.selectComments(displayInfoId);
		for (Comment comment : comments) {
			List<CommentImage> list = DetailDao.selectCommentsImagesByCommentId(comment.getCommentId());
			comment.setCommentImages(list);
		}
		return comments;
	}

	@Override
	public Double getAverageScore(Long displayInfoId) {
		return DetailDao.selectAverageScore(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(Long displayInfoId) {
		return DetailDao.selectProductPrices(displayInfoId);
	}
}
