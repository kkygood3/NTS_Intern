/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.model.ProductResponse;
import com.nts.reservation.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = {"/api/products"})
	public ProductResponse getProductList(@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProductResponse(categoryId, start);
	}
}
