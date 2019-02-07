/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.resevation.dto.CategoryDto;
import com.nts.resevation.service.CategoryService;

/**
 * 카테고리에 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 모든 카테고리 정보들을 json형태로 반환해준다 
	 */
	@GetMapping
	public Map<String, Object> getCategoriesWithProductsOnDisplayCount() {
		List<CategoryDto> categories = categoryService.getCategoriesWithProductsOnDisplayCount();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categories", categories);

		return map;
	}
}
