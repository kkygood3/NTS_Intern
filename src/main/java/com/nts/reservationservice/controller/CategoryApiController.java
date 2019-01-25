/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservationservice.dto.CategoryDto;
import com.nts.reservationservice.service.CategoryService;
import com.nts.reservationservice.service.ProductService;

/*
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> list() {

		Map<String, Object> map = new HashMap<>();
		List<CategoryDto> items = categoryService.getCategorys();

		map.put("items", items);
		return map;
	}
}
