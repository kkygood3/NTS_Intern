/**
 * Copyright 2015 NaverCorp. All rights Reserved.
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

/**
* FileName : ReservationApiController.java
* @author  : 이승수
* @date    : 2019. 1. 28.
*/
@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {
	@Autowired
	ProductService productService;

	@GetMapping(path = "/products")
	public Map<String, Object> getProducts(@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<Product> products = productService.getProducts(categoryId, start);
		int countProducts = 0;
		if (categoryId == 0) {
			countProducts = productService.getCountAllProducts();
		} else {
			countProducts = productService.getCount(categoryId);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("items", products);
		map.put("totalCount", countProducts);

		return map;
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> getPromotions() {
		List<Product> promotions = productService.getPromotions();
		int countProducts = productService.getCountPromotions();

		Map<String, Object> map = new HashMap<>();
		map.put("items", promotions);
		map.put("totalCount", countProducts);

		return map;
	}
}
