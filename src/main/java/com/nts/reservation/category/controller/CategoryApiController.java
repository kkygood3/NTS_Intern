/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class CategoryApiController {
	@Autowired
	private CategoryService categoryServiceImpl;

	@GetMapping("/api/categories")
	public CategoryResponse getItems() {
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setItems(categoryServiceImpl.getItems());

		return categoryResponse;
	}

}
