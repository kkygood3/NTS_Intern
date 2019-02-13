/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.promotion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.promotion.dao.impl.PromotionDaoImpl;
import com.nts.reservation.promotion.dto.PromotionResponse;
import com.nts.reservation.promotion.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDaoImpl promotionDaoImpl;

	@Override
	public PromotionResponse getPromotions() {
		PromotionResponse promotionResponse = new PromotionResponse();

		promotionResponse.setItems(promotionDaoImpl.selectPagingCategories());

		return promotionResponse;
	}

}
