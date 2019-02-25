/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.category.Categories;
import com.nts.service.category.CategoryService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * @desc 카테고리 전체 불러오기
	 * @return items { 카테고리 리스트}
	 * @throws IOException 
	 */
	@GetMapping
	public Categories getCategories() {
		return categoryService.getCategories();
	}
}
