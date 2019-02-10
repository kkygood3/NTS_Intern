/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.PromotionService;

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
