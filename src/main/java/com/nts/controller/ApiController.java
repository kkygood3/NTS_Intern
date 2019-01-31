/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.MainPageCategory;
import com.nts.dto.MainPageProduct;
import com.nts.dto.MainPagePromotion;
import com.nts.service.MainPageCategoryService;
import com.nts.service.MainPageProductService;
import com.nts.service.MainPagePromotionService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private MainPageProductService productApiViewService;
	@Autowired
	private MainPageCategoryService categoryApiViewService;
	@Autowired
	private MainPagePromotionService promotionApiViewService;

	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<MainPageProduct> items;
		items = productApiViewService.getProducts(categoryId, start);

		int totalCount = productApiViewService.getCount(categoryId);

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}

	@GetMapping("/categories")
	public Map<String, Object> categories() {

		List<MainPageCategory> items = categoryApiViewService.getAllCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("items", items);

		return map;
	}

	@GetMapping("/promotions")
	public Map<String, Object> promotions() {

		List<MainPagePromotion> items = promotionApiViewService.getAllPromotions();
		int totalCount = promotionApiViewService.getCount();

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}
}
