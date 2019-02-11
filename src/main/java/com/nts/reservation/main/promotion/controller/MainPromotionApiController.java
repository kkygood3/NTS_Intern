/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.promotion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.main.promotion.dto.MainPromotion;
import com.nts.reservation.main.promotion.service.MainPromotionService;

@RestController
@RequestMapping("/api")
public class MainPromotionApiController {

	@Autowired
	private MainPromotionService mainPromotionService;

	/**
	 * /promotions API요청시 promotion 관련 id, ImageUrl을 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/promotions")
	public Map<String, Object> promotions() {
		List<MainPromotion> promotionList = new ArrayList<>();
		promotionList = mainPromotionService.getPromotions();

		Map<String, Object> map = new HashMap<>();
		map.put("items", promotionList);

		return map;
	}
}
