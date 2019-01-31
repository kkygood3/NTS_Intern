/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.model.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * category 목록을 Json으로 응답
	 */
	@GetMapping(value = {"/api/categories"})
	public CategoryResponse getCategoryList() {
		return new CategoryResponse(categoryService.getCategoryList());
	}
}
