package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.detail.DisplayInfoResponse;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DetailService;
import com.nts.reservation.service.ProductService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/", method = {RequestMethod.GET})
public class ApplicationProductApiController {
	@Autowired
	private ProductService productService;
	@Autowired
	private DetailService detailService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/products")
	public Map<String, Object> getProductsByCategory(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start) {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getProductsByCategory(categoryId, start));
		if (categoryId == 0) {
			result.put("totalCount", productService.getProductsCount());
		} else {
			result.put("totalCount", productService.getProductsCountByCategory(categoryId));
		}

		return result;
	}

	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoResponse getProductDetailByDisplayInfoId(
		@PathVariable(name = "displayInfoId", required = false) Long displayInfoId) {
		DisplayInfoResponse result = new DisplayInfoResponse.Builder()
			.displayInfo(detailService.getDisplayInfo(displayInfoId))
			.productImages(detailService.getProductImages(displayInfoId))
			.displayInfoImage(detailService.getDisplayInfoImage(displayInfoId))
			.averageScore(detailService.getAverageScore(displayInfoId))
			.productPrices(detailService.getProductPrices(displayInfoId))
			.comments(detailService.getComments(displayInfoId))
			.build();
		return result;
	}

	@GetMapping("/categories")
	public Map<String, Object> getAllProductsCountByCategory() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", categoryService.getAllProductsCountByCategory());
		return result;
	}

	@GetMapping("/promotions")
	public Map<String, Object> getPromotions() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getPromotions());
		return result;
	}

}
