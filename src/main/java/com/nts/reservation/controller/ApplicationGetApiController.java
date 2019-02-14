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

import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DetailService;
import com.nts.reservation.service.ProductService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/", method = {RequestMethod.GET})
public class ApplicationGetApiController {
	@Autowired
	ProductService productService;
	@Autowired
	DetailService detailService;
	@Autowired
	CategoryService categoryService;

	@GetMapping("/products")
	public Map<String, Object> selectProductsByCategory(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start) {

		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.selectProductsByCategory(categoryId, start));
		if (categoryId == 0) {
			result.put("totalCount", productService.selectProductsCount());
		} else {
			result.put("totalCount", productService.selectProductsCountByCategory(categoryId));
		}

		return result;
	}

	@GetMapping("/products/{displayInfoId}")
	public DetailResponse selectProductDetailByDisplayInfoId(
		@PathVariable(name = "displayInfoId", required = false) Long displayInfoId) {
		DetailResponse result = new DetailResponse.Builder()
			.displayInfo(detailService.selectDisplayInfo(displayInfoId))
			.productImages(detailService.selectProductImages(displayInfoId))
			.displayInfoImage(detailService.selectDisplayInfoImage(displayInfoId))
			.averageScore(detailService.selectAverageScore(displayInfoId))
			.productPrices(detailService.selectProductPrices(displayInfoId))
			.comments(detailService.selectComments(displayInfoId))
			.build();
		return result;
	}

	@GetMapping("/categories")
	public Map<String, Object> selectAllProductsCountByCategory() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", categoryService.selectAllProductsCountByCategory());
		return result;
	}

	@GetMapping("/promotions")
	public Map<String, Object> selectPromotions() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.selectPromotions());
		return result;
	}

	@GetMapping("/reservations")
	public Map<String, Object> selectReservation() {
		Map<String, Object> result = new HashMap<>();
		return result;
	}
}
