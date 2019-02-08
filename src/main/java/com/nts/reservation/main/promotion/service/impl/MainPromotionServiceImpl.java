/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.promotion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.main.promotion.dao.MainPromotionDao;
import com.nts.reservation.main.promotion.dto.MainPromotion;
import com.nts.reservation.main.promotion.service.MainPromotionService;

@Service
public class MainPromotionServiceImpl implements MainPromotionService {

	@Autowired
	MainPromotionDao mainPromotionDao;

	@Override
	public List<MainPromotion> getPromotions() {
		return mainPromotionDao.selectPagingCategories();
	}

}
