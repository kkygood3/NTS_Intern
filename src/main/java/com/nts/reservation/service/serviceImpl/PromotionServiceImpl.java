/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.PromotionResponse;
import com.nts.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	public PromotionResponse getPromotions() {
		return PromotionResponse.builder()
			.items(promotionDao.getPromotions()).build();
	}
	
}
