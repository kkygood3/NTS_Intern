/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.common.CommentDao;
import com.nts.reservation.dao.review.ReviewDisplayInfoDao;
import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	CommentDao commentDao;
	@Autowired
	ReviewDisplayInfoDao reviewDisplayInfoDao;

	@Override
	@Transactional(readOnly = true)
	public ReviewResponse getReviewResponse(int displayInfoId, int start, int pagingLimit) {
		ReviewResponse reviewResponse = new ReviewResponse();

		reviewResponse.setReviewComment(commentDao.selectComment(displayInfoId, start, pagingLimit));
		reviewResponse.setReviewDisplayInfo(reviewDisplayInfoDao.selectDetailDisplayInfo(displayInfoId));
		return reviewResponse;
	}
}
