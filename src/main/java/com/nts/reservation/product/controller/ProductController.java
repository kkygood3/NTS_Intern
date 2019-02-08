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
import com.nts.reservation.utils.Validator;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping(path = "/products")
	public ProductResponse productList(@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start){
		
		if(Validator.isCategoryInvaild(categoryId, start)) {
			throw new NumberFormatException("BAD_REQUEST!!<br> Please try agin...");
		}
		return productService.getProductsByCategory(categoryId, start);
	}
}
