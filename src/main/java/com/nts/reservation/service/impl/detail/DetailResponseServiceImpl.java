/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.detail.DetailResponseDao;
import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.detail.DetailResponseService;

@Service
public class DetailResponseServiceImpl implements DetailResponseService {
	@Autowired
	DetailResponseDao detailPageDao;

	@Override
	public List<DetailResponse> getDetailResponse(int displayInfoId, int pagingLimit) {
		return detailPageDao.selectDetailResponsePageByDisplayInfoId(displayInfoId, pagingLimit);
	}

}
