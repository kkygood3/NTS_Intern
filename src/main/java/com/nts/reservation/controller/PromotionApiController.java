/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.PromotionService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class PromotionApiController {
	@Autowired
	PromotionService promotionService;

	@GetMapping("/api/promotions")
	public Map<String, Object> getProductList() {
		List<Promotion> promotionList = new ArrayList<>();
		int promotionCount = promotionService.getPromotionsCount();
		if (promotionCount != 0) {
			promotionList = promotionService.getPromotions();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("promotionList", promotionList);
		map.put("promotionCount", promotionCount);

		return map;
	}
}
