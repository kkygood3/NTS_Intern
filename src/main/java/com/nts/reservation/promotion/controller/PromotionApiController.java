/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.dto.PromotionResponse;
import com.nts.reservation.promotion.service.PromotionService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping("/api/promotions")
	public PromotionResponse getItems() {
		int promotionCount = promotionService.getPromotionsCount();
		if (promotionCount == 0) {
			return getEmptyItems();
		}

		PromotionResponse promotionResponse = new PromotionResponse();
		promotionResponse.setItems(promotionService.getPromotions());

		return promotionResponse;
	}

	private PromotionResponse getEmptyItems() {
		PromotionResponse promotionResponse = new PromotionResponse();
		promotionResponse.setItems(Collections.emptyList());

		return promotionResponse;
	}
}
