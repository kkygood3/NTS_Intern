package com.nts.reservation.service.impl;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.Iterator;
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
		List<CommentImage> commentImages = DetailDao.getCommentsImages(displayInfoId);
		List<Comment> comments = DetailDao.getComments(displayInfoId);
		Iterator<CommentImage> imgIter = commentImages.iterator();
		Iterator<Comment> commIter = comments.iterator();
		/* since all the comments/images are in DESC order, 
		 * we can simply iterate and put images to comments
		 */
		while (imgIter.hasNext()) {
			boolean isFound = false;
			CommentImage currentImage = imgIter.next();
			Long commentId = currentImage.getReservationInfoId();
			while (commIter.hasNext()) {
				Comment currentComment = commIter.next();
				if (commentId == currentComment.getCommentId()) {
					isFound = true;
					currentComment.getCommentImages().add(currentImage);
					break;
				}
			}
			if (!isFound) {
				break;
			}
		}
		return comments;
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
