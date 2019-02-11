/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 한번에 많은 양의 db조회를 막기 위함
	private static final int MAX_LIMIT = 20;

	@GetMapping(path = "/products")
	public ProductResponse productList(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (limit > MAX_LIMIT) {
			limit = MAX_LIMIT;
		}

		return productService.getProductsByCategory(categoryId, start, limit);
	}
}
