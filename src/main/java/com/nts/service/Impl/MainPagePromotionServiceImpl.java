/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.MainPagePromotionDao;
import com.nts.dto.MainPagePromotion;
import com.nts.service.MainPagePromotionService;

@Service
public class MainPagePromotionServiceImpl implements MainPagePromotionService {

	@Autowired
	private MainPagePromotionDao promotionApiViewDao;

	@Override
	public List<MainPagePromotion> getAllPromotions() {
		return promotionApiViewDao.selectAllPromotions();
	}

	@Override
	public int getCount() {
		return promotionApiViewDao.selectCount();
	}

}
