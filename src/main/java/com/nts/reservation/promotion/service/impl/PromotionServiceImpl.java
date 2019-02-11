/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.dto.Promotion;
import com.nts.reservation.promotion.service.PromotionService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionDao promotionDao;

	@Override
	public List<Promotion> getPromotions() {
		return promotionDao.selectPromotions(PROMOTION_LIMIT);
	}

	@Override
	public int getPromotionsCount() {
		return promotionDao.selectPromotionsCount();
	}
}
