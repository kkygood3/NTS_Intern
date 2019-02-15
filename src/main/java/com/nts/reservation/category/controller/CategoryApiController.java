/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryServiceImpl;

	/**
	 * /categories API요청시 category 관련 id, name, count를 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/categories")
	public CategoryResponse categories() {
		return categoryServiceImpl.getCategories();
	}
}
