/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.ProductService;

/*
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class MainPageApiController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public Map<String, Object> getProductList(
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId", required = false) Integer categoryId) {

		Map<String, Object> map = new HashMap<>();
		List<ProductDto> items = Collections.emptyList();
		int count = 0;

		if (categoryId == null) {
			items = productService.getProducts(start);
			count = productService.getCount();
		} else {
			items = productService.getProducts(start, categoryId);
			count = productService.getCount(categoryId);
		}

		map.put("items", items);
		map.put("totalCount", count);
		return map;
	}

	@GetMapping("/promotions")
	public Map<String, Object> getPromotionList() {

		Map<String, Object> map = new HashMap();
		List<ProductDto> items = productService.getPromotionProducts();
		map.put("items", items);
		map.put("totalCount", items.size());
		return map;
	}

	@GetMapping("/categories")
	public Map<String, Object> getCategoryList() {

		Map<String, Object> map = new HashMap<>();
		List<CategoryDto> items = categoryService.getCategorys();
		map.put("items", items);
		return map;
	}
}
