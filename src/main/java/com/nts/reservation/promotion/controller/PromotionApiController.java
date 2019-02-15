/**
 * Copyright 2015 Naver Corp. All rights Reserved.
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
@RequestMapping("/api")
public class PromotionApiController {

	@Autowired
	private PromotionService promotionServiceImpl;

	/**
	 * /promotions API요청시 promotion 관련 id, ImageUrl을 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/promotions")
	public PromotionResponse promotions() {
		return promotionServiceImpl.getPromotions();
	}
}
