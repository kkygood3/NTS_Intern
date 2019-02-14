/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.dto.PromotionResponse;
import com.nts.reservation.promotion.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	@Override
	public PromotionResponse getPromotions() {
		return PromotionResponse.builder()
			.items(promotionDao.selectAllPromotions()).build();
	}

}
