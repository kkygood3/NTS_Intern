/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.main.product.dto.MainProduct;
import com.nts.reservation.main.product.service.MainProductService;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private MainProductService mainProductService;

	/**
	 * /products API요청시 totalCount와 product 관련 정보 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId) {

		List<MainProduct> productList = new ArrayList<>();
		int totalCount = mainProductService.getCount(categoryId);

		if (totalCount > 0) {
			productList = mainProductService.getProducts(categoryId, start);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("items", productList);
		map.put("totalCount", totalCount);

		return map;
	}
}
