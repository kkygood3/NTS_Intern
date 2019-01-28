/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.CategoryDto;
import com.nts.service.CategoryService;

/**
 * 카테고리에 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;

	/**
	 * 모든 카테고리 정보들을 json형태로 반환해준다 
	 */
	@GetMapping
	public Map<String, Object> list() {
		List<CategoryDto> list = categoryService.getCategoriesWithProductCount();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", list);

		return map;
	}
}
