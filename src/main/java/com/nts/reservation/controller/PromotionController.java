/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.model.PromotionResponse;
import com.nts.reservation.service.PromotionService;

@RestController
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	/**
	 * promotion 목록을 Json으로 응답
	 */
	@GetMapping(value = {"/api/promotions"})
	public PromotionResponse getPromotionList() {
		return promotionService.getPromotionResponse();
	}
}
