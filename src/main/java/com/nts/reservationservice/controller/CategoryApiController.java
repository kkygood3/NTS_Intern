/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservationservice.dto.CategoryDto;
import com.nts.reservationservice.service.CategoryService;
import com.nts.reservationservice.service.ProductService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> list() {

		Map<String, Object> map = new HashMap<>();
		List<CategoryDto> list = categoryService.getCategorys();

		List<Map<String, Object>> items = new ArrayList();
		for (CategoryDto item : list) {
			int count = productService.getCount(item.getId());
			Map<String, Object> combineItem = combineDataForGetResult(item, count);
			items.add(combineItem);
		}

		map.put("items", items);
		return map;
	}

	public Map<String, Object> combineDataForGetResult(CategoryDto category, int count) {
		Map<String, Object> map = new HashMap();

		map.put("count", count);
		map.put("id", category.getId());
		map.put("name", category.getName());

		return map;
	}
}
