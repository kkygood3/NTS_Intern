/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.layer.promotion.dto.PromotionResponse;
import com.nts.layer.promotion.service.PromotionService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public PromotionResponse selectPromotions() {
		return promotionService.selectPromotionAll();
	}
}
