/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.PromotionDto;
import com.nts.service.PromotionService;

/**
 * 프로모션 목록 구하기 web api
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/promotions")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	/**
	 *  프로모션 목록들을 가져와 json으로 리턴
	 */
	@GetMapping
	public Map<String, Object> list() {
		List<PromotionDto> list = promotionService.getPromotions();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", list);

		return map;
	}
}
