/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.category.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.main.category.dto.Category;
import com.nts.reservation.main.category.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/api/categories")
	public Map<String, List<Category>> getCategoryList() {
		List<Category> categoryList = categoryService.getCategoryList();

		Map<String, List<Category>> map = new HashMap<>();
		map.put("categoryList", categoryList);

		return map;
	}

}
