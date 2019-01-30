package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

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
	public Map<String, Object> getProductsByCategory(@RequestParam(name = "start", required = true) Integer start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer category) {
		return productService.getProductsByCategory(start, category);
	}

	@GetMapping("/categories")
	public Map<String, Object> getCountsByCategory() {
		return productService.getProductsCountByCategory();
	}

	@GetMapping("/promotions")
	public Map<String, Object> promotionList() {
		return productService.getPromotions();
	}
}
