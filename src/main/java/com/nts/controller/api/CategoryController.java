/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.layer.category.service.CategoryService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @desc category 리스트 
	 * @return categories -> 카테고리들
	 */
	@GetMapping
	public Map<String, Object> getCategorys() {

		Map<String, Object> categoryMap = new HashMap<>();

		categoryMap.put("categories", categoryService.selectCategories());
		return categoryMap;
	}
}
