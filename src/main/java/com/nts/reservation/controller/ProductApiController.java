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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.ProductService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/")
public class ProductApiController {
	@Autowired
	ProductService productService;

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

	@GetMapping("/categories")
	public Map<String, Object> getAllProductsCountByCategory() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getAllProductsCountByCategory());
		return result;
	}

	@GetMapping("/promotions")
	public Map<String, Object> getPromotions() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getPromotions());
		return result;
	}
}
