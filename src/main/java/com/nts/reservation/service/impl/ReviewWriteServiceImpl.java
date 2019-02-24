/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.common.CommentDao;
import com.nts.reservation.dao.review.ReviewDisplayInfoDao;
import com.nts.reservation.dao.reviewwrite.ReviewWrtieDao;
import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;
import com.nts.reservation.service.ReviewService;
import com.nts.reservation.service.ReviewWriteService;

@Service
public class ReviewWriteServiceImpl implements ReviewWriteService {
	@Autowired
	ReviewWrtieDao reviewWrtieDao;

	@Override
	public ReviewWriteResponse getReviewWriteResponse(int reservationInfoId) {
		return reviewWrtieDao.selectReviewWrite(reservationInfoId);
	}
}
