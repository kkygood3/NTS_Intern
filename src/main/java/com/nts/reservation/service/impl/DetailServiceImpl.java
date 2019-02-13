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
		List<Comment> comments = DetailDao.getComments(displayInfoId);
		for (Comment comm : comments) {
			List<CommentImage> list = DetailDao.getCommentsImagesByCommentId(comm.getCommentId());
			comm.setCommentImages(list);
		}

		return comments;

		/*  
		 * @Deprecated way through review process
		 * 
		 * fetch possible candidates of images and map images in iteration process. 
		 * total time complexity = O(M+N)
		 * total space complexity = O(M+2N)
		 * * N = images.length M = comments.length
		 * 2 data fetch from db
		 * 
		 * Below is the past algorithm for other strategy for mapping comment images to comment, simply fetch all rows, and map through iteration
		 */
		//		if (!commIter.hasNext()) {
		//			return comments;
		//		}
		//		while (imgIter.hasNext()) {
		//			boolean isFound = false;
		//			CommentImage currentImage = imgIter.next();
		//			Long commentId = currentImage.getReservationInfoId();
		//			if (currentComment.getCommentId() == commentId) {
		//				currentComment.getCommentImages().add(currentImage);
		//			} else {
		//				while (commIter.hasNext()) {
		//					currentComment = commIter.next();
		//					if (commentId == currentComment.getCommentId()) {
		//						isFound = true;
		//						currentComment.getCommentImages().add(currentImage);
		//						break;
		//					}
		//				}
		//				if (!isFound) {
		//					break;
		//				}
		//			}
		//		}

	}

	@Override
	public Double getAverageScore(Long displayInfoId) {
		return DetailDao.getAverageScore(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(Long displayInfoId) {
		return DetailDao.getProductPrices(displayInfoId);
	}
}
