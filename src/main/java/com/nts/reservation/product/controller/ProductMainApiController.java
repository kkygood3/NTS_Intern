/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductMainApiController {
	@Autowired
	private ProductService productService;

	@GetMapping("/api/products")
	public ProductResponse getItems(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (isInvalidParameter(categoryId, start)) {
			System.out.println("올바르지 않은 categoryId 또는 start");

			return getEmptyitems();
		}

		int totalCount = productService.getProductsCountByCategoryId(categoryId);
		if (totalCount == 0) {
			return getEmptyitems();
		}

		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(totalCount);
		productResponse.setItems(productService.getProductsByCategoryId(categoryId, start, limit));

		return productResponse;
	}

	private boolean isValidParameter(int categoryId, int limit) {
		if (categoryId < 0 || limit < 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidParameter(int categoryId, int limit) {
		return !isValidParameter(categoryId, limit);
	}

	private ProductResponse getEmptyitems() {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(0);
		productResponse.setItems(Collections.emptyList());

		return productResponse;
	}
}
