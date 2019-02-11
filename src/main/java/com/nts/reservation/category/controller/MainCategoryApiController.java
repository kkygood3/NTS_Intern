/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.MainCategory;
import com.nts.reservation.category.service.MainCategoryService;

@RestController
@RequestMapping("/api")
public class MainCategoryApiController {

	@Autowired
	private MainCategoryService mainCategoryService;

	/**
	 * /categories API요청시 category 관련 id, name, count를 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/categories")
	public Map<String, Object> categories() {
		List<MainCategory> categoryList = new ArrayList<>();
		categoryList = mainCategoryService.getCategories();

		Map<String, Object> map = new HashMap<>();
		map.put("items", categoryList);

		return map;
	}
}
