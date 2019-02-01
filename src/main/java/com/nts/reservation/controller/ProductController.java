/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public Map<String, Object> productList(@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start){
		
		int totalCount = productService.getNumberOfProducts(categoryId);
//		int pageCount = count / ProductService.LIMIT;
//		if( count % ProductService.LIMIT > 0) {
//			pageCount++;
//		}
		
		List<Product> products = productService.getProductByCategory(categoryId, start);
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("products", products);
		
		return map;
	}
}
