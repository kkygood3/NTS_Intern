/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.CategoryItems;
import com.nts.reservation.category.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class CategoryApiController {
	private CategoryItems categoryItems = new CategoryItems();

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/api/categories")
	public CategoryItems getCategoryList() {
		categoryItems.setCategoryList(categoryService.getCategoryList());

		return categoryItems;
	}

}
