/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.dto.PromotionResponse;
import com.nts.reservation.promotion.service.PromotionService;

@RestController
@RequestMapping(path = "/api")
public class PromotionApiController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping(path = "/promotions")
	public PromotionResponse getPromotions() {
		return promotionService.getPromotions();
	}

}
