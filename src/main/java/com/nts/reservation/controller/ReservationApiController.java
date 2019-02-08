/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.MainCategory;
import com.nts.reservation.dto.MainProduct;
import com.nts.reservation.dto.MainPromotion;
import com.nts.reservation.service.MainCategoryService;
import com.nts.reservation.service.MainProductService;
import com.nts.reservation.service.MainPromotionService;

@RestController
@RequestMapping("/api")
public class ReservationApiController {

	@Autowired
	private MainProductService mainProductService;

	@Autowired
	private MainCategoryService mainCategoryService;

	@Autowired
	private MainPromotionService mainPromotionService;

	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId) {

		List<MainProduct> productList = new ArrayList<>();
		int totalCount = mainProductService.getCount(categoryId);

		if (totalCount > 0) {
			productList = mainProductService.getProducts(categoryId, start);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("items", productList);
		map.put("totalCount", totalCount);

		return map;
	}

	@GetMapping("/categories")
	public Map<String, Object> categories() {
		List<MainCategory> categoryList = new ArrayList<>();
		categoryList = mainCategoryService.getCategories();

		Map<String, Object> map = new HashMap<>();
		map.put("items", categoryList);

		return map;
	}

	@GetMapping("/promotions")
	public Map<String, Object> promotions() {
		List<MainPromotion> promotionList = new ArrayList<>();
		promotionList = mainPromotionService.getPromotions();

		Map<String, Object> map = new HashMap<>();
		map.put("items", promotionList);

		return map;
	}
}
