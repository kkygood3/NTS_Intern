/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.ProductResponse;
import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public ProductResponse productList(@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start){
		
		return productService.getProductsByCategory(categoryId, start);
	}
}
