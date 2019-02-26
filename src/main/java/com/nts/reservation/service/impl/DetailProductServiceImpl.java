/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.DetailProductDao;
import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;
import com.nts.reservation.service.DetailProductService;

/**
* @author  : 이승수
*/
@Service
public class DetailProductServiceImpl implements DetailProductService {
	@Autowired
	private DetailProductDao detailProductDao;

	@Override
	public BigDecimal getAverageScore(int displayInfoId) {
		BigDecimal avgScore = BigDecimal.valueOf(0);
		if (detailProductDao.selectAverageScore(displayInfoId) != null) {
			avgScore = detailProductDao.selectAverageScore(displayInfoId)
				.setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		return avgScore;
	}

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comments = detailProductDao.selectComments(displayInfoId);
		for (Comment c : comments) {
			int commentId = c.getCommentId();
			List<CommentImage> commentImages = detailProductDao.selectCommentImages(commentId);
			c.setCommentImages(commentImages);
		}
		return comments;
	}

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return detailProductDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return detailProductDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return detailProductDao.getProductImages(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return detailProductDao.selectProductPrices(displayInfoId);
	}
}