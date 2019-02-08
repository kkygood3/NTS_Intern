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
	DetailProductDao detailProductDao;

	@Override
	public BigDecimal getAverageScore(Integer displayInfoId) {
		BigDecimal avgScore = BigDecimal.valueOf(0);
		if (detailProductDao.getAverageScore(displayInfoId) != null) {
			avgScore = detailProductDao.getAverageScore(displayInfoId)
				.setScale(1, BigDecimal.ROUND_HALF_UP);
		}
		return avgScore;
	}

	@Override
	public List<Comment> getComments(Integer displayInfoId) {
		List<Comment> comments = detailProductDao.getComments(displayInfoId);
		for (Comment c : comments) {
			int commentId = c.getCommentId();
			List<CommentImage> commentImages = detailProductDao.getCommentImages(commentId);
			c.setCommentImages(commentImages);
		}
		return comments;
	}

	@Override
	public DisplayInfo getDisplayInfo(Integer displayInfoId) {
		return detailProductDao.getDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
		return detailProductDao.getDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(Integer displayInfoId) {
		return detailProductDao.getProductImages(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		return detailProductDao.getProductPrices(displayInfoId);
	}
}
