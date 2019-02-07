/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.promotion.Promotions;
import com.nts.service.promotion.PromotionService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	/**
	 * @desc 프로모션 전체 불러오기
	 * @return promotionResponse { items: promotion List } 
	 */
	@GetMapping
	public Promotions getPromotions() {
		return promotionService.getPromotions();
	}
}
