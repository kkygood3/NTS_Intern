/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.PromotionDto;
import com.nts.reservation.service.PromotionService;

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
	public Map<String, Object> getPromotions(
		@RequestParam(required = false, defaultValue = PROMOTIONS_LIMIT) int limit,
		@RequestParam(required = false, defaultValue = IMAGE_TYPE_THUMBNAIL) String type) {
		List<PromotionDto> promotions = promotionService.getPromotions(limit, ImageType.getEnum(type));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("promotions", promotions);

		return map;
	}
}
