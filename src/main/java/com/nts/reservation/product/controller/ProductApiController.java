/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@GetMapping("/api/products")
	public Map<String, Object> getProductList(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		int productCount = productService.getProductsCountByCategoryId(categoryId);
		if (productCount == 0) {
			//return Collections.emptyList();
		}

		List<Product> productList = productService.getProducts(categoryId, start, limit);

		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("productCount", productCount);

		return map;

		// productCount 처리할 것
		//return productService.getProducts(categoryId, start);
	}
}
