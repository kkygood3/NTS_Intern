/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.detail.DetailExtraImageDao;
import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.service.detail.DetailExtraImageService;

@Service
public class DetailExtraImageServiceImpl implements DetailExtraImageService {
	@Autowired
	DetailExtraImageDao detailExtraImageDao;

	@Override
	public DetailExtraImage getExtraImage(int displayInfoId) {
		return detailExtraImageDao.selectDetailExtraImageById(displayInfoId);
	}

}
