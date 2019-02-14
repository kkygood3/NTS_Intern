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
	public DisplayInfo selectDisplayInfo(Long displayInfoId) {
		return DetailDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public List<ProductImage> selectProductImages(Long displayInfoId) {
		return DetailDao.selectProductImages(displayInfoId);
	}

	@Override
	public DisplayInfoImage selectDisplayInfoImage(Long displayInfoId) {
		return DetailDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<Comment> selectComments(Long displayInfoId) {
		List<Comment> comments = DetailDao.selectComments(displayInfoId);
		for (Comment comm : comments) {
			List<CommentImage> list = DetailDao.selectCommentsImagesByCommentId(comm.getCommentId());
			comm.setCommentImages(list);
		}

		return comments;
	}

	@Override
	public double selectAverageScore(Long displayInfoId) {
		return DetailDao.selectAverageScore(displayInfoId);
	}

	@Override
	public List<ProductPrice> selectProductPrices(Long displayInfoId) {
		return DetailDao.selectProductPrices(displayInfoId);
	}
}
