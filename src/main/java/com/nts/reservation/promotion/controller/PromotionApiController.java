/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.dto.PromotionItems;
import com.nts.reservation.promotion.service.PromotionService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping("/api/promotions")
	public PromotionItems getProductList() {
		int promotionCount = promotionService.getPromotionsCount();
		if (promotionCount == 0) {
			return getEmptyPromotionList();
		}

		PromotionItems promotionItems = new PromotionItems();
		promotionItems.setPromotionCount(promotionCount);
		promotionItems.setPromotionList(promotionService.getPromotions());

		return promotionItems;
	}

	private PromotionItems getEmptyPromotionList() {
		PromotionItems promotionItems = new PromotionItems();
		promotionItems.setPromotionCount(0);
		promotionItems.setPromotionList(Collections.emptyList());

		return promotionItems;
	}
}
