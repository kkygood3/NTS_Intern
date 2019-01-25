/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.DisplayInfoDao;
import com.nts.reservationservice.dto.DisplayInfoDto;
import com.nts.reservationservice.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	@Autowired
	DisplayInfoDao displayInfoDao;

	@Override
	@Transactional
	public DisplayInfoDto getDisplayInfoByProductId(Long productId) {

		return displayInfoDao.selectDisplayInfoByProductId(productId);
	}

}
