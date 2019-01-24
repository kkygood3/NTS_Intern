/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservationservice.dto.ProductDto;
import com.nts.reservationservice.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId", required = false) Integer categoryId) {

		Map<String, Object> map = new HashMap<>();
		List<ProductDto> list = Collections.emptyList();
		int count = -1;

		if (categoryId == null) {
			list = productService.getProducts(start);
			count = productService.getCount();
		} else {
			list = productService.getProducts(start, categoryId);
			count = productService.getCount(categoryId);
		}

		map.put("items", list);
		map.put("totalCount", count);
		return map;
	}

}
