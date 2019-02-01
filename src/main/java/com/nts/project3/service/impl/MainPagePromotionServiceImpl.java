/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.MainPagePromotionDao;
import com.nts.project3.dto.MainPagePromotion;
import com.nts.project3.service.MainPagePromotionService;

@Service
public class MainPagePromotionServiceImpl implements MainPagePromotionService {

	@Autowired
	private MainPagePromotionDao mainPagePromotionDao;

	@Override
	public List<MainPagePromotion> getPromotions() {
		return mainPagePromotionDao.selectPromotions(LIMIT);
	}

	@Override
	public int getCount() {
		return mainPagePromotionDao.selectCount();
	}

}
