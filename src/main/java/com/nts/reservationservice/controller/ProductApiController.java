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
import com.nts.reservationservice.service.DisplayInfoService;
import com.nts.reservationservice.service.FileInfoService;
import com.nts.reservationservice.service.ProductImageService;
import com.nts.reservationservice.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;
	@Autowired
	DisplayInfoService displayInfoService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	FileInfoService fileInfoService;

	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId", required = false) Integer categoryId) {

		Map<String, Object> map = new HashMap<>();
		List<ProductDto> items = Collections.emptyList();
		int count = -1;

		if (categoryId == null) {
			items = productService.getProducts(start);
			count = productService.getCount();
		} else {
			items = productService.getProducts(start, categoryId);
			count = productService.getCount(categoryId);
		}

		map.put("items", items);
		map.put("totalCount", count);
		return map;
	}
}
