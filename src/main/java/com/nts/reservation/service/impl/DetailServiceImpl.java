/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.common.CommentDao;
import com.nts.reservation.dao.detail.DetailDisplayInfoDao;
import com.nts.reservation.dao.detail.DetailExtraImageDao;
import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	CommentDao detailCommentDao;
	@Autowired
	DetailDisplayInfoDao detailDisplayInfoDao;
	@Autowired
	DetailExtraImageDao detailExtraImageDao;

	@Override
	public DetailResponse getDetailResponse(int displayInfoId, int start, int pagingLimit) {
		DetailResponse detailResponse = new DetailResponse();

		detailResponse.setDetailComment(detailCommentDao.selectCommentByDisplayInfoId(displayInfoId, start, pagingLimit));
		detailResponse.setDetailDisplayInfo(detailDisplayInfoDao.selectDetailDisplayInfoByDisplayInfoId(displayInfoId));

		return detailResponse;
	}

	@Override
	public DetailExtraImage getExtraImage(int displayInfoId) {
		return detailExtraImageDao.selectDetailExtraImageById(displayInfoId);
	}
}
