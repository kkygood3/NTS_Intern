/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.model.PromotionResponse;
import com.nts.reservation.promotion.service.PromotionService;

@RestController
public class PromotionApiController {

	private final PromotionService promotionService;

	@Autowired
	public PromotionApiController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	/**
	 * promotion 목록을 Json으로 응답
	 */
	@GetMapping(value = {"/api/promotions"})
	public PromotionResponse getPromotionResponse() {
		return new PromotionResponse(promotionService.getPromotionList());
	}
}
