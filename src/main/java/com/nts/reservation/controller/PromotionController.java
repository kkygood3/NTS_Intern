/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.PromotionResponse;
import com.nts.reservation.service.PromotionService;

@RestController
@RequestMapping(path = "/promotions")
public class PromotionController {
	
	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	public PromotionResponse getPromotions(){
		return promotionService.getPromotions();
	}
	
}
