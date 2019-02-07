/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.MainPromotionDao;
import com.nts.reservation.dto.MainPromotion;
import com.nts.reservation.service.MainPromotionService;

@Service
public class MainPromotionServiceImpl implements MainPromotionService {

	@Autowired
	private MainPromotionDao mainPromotionDao;

	@Override
	public List<MainPromotion> getPromotions() {
		return mainPromotionDao.selectPromotions(LIMIT);
	}

	@Override
	public int getCount() {
		return mainPromotionDao.selectCount();
	}

}
