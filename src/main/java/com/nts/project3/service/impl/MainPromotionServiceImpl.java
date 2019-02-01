/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.MainPromotionDao;
import com.nts.project3.dto.MainPromotion;
import com.nts.project3.service.MainPromotionService;

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
