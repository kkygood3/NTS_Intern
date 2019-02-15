/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.detail.DetailCommentDao;
import com.nts.reservation.dao.detail.DetailDisplayInfoDao;
import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.detail.DetailResponseService;

@Service
public class DetailResponseServiceImpl implements DetailResponseService {
	@Autowired
	DetailCommentDao detailCommentDao;
	@Autowired
	DetailDisplayInfoDao detailDisplayInfoDao;
	
	@Override
	public DetailResponse getDetailResponse(int displayInfoId, int pagingLimit) {
		DetailResponse detailResponse = new DetailResponse();
		detailResponse.setDetailComment(detailCommentDao.selectCommentPageByDisplayInfoId(displayInfoId, pagingLimit));
		detailResponse.setDetailDisplayInfo(detailDisplayInfoDao.selectDetailDisplayInfoByDisplayInfoId(displayInfoId));
		return detailResponse;
	}

}
