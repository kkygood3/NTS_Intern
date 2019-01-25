/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.model.PromotionResponse;
import com.nts.reservation.service.PromotionService;

@Service
public class PromotionServiceLogic implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	@Override
	public PromotionResponse getPromotionResponse() {
		return new PromotionResponse(promotionDao.getPromotionList());
	}

}
