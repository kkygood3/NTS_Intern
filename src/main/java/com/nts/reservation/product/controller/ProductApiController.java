/**
 * Copyright 2015 Naver Corp. All rights Reserved.
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
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private ProductService productService;

	/**
	 * /products API요청시 totalCount와 product 관련 정보 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/products")
	public ProductResponse products(
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId) {

		if(isNegativeValue(start, categoryId)) {
			System.out.printf("허용되지 않은 파라미터 시도입니다. start : %d, categoryId : %d\n", start, categoryId);
			start = 0;
			categoryId = 0;
		}

		return productService.getProducts(categoryId, start);
	}

	private boolean isNegativeValue(int start, int categoryId) {
		if(start < 0 || categoryId < 0) {
			return true;
		}
		return false;
	}
}
