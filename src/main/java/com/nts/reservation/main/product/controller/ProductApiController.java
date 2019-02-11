/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.main.product.dto.Product;
import com.nts.reservation.main.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping("/api/products")
	public Map<String, Object> getProductList(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<Product> productList = new ArrayList<>();
		int productCount = productService.getProductsCountByCategoryId(categoryId);
		if (productCount > 0) {
			productList = productService.getProducts(categoryId, start);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("productCount", productCount);

		return map;
	}
}
