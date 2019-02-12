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

import com.nts.reservation.product.dto.ProductItems;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@GetMapping("/api/products")
	public ProductItems getProductList(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (isInvalidParameter(categoryId, start)) {
			System.out.println("올바르지 않은 categoryId 또는 start");

			return getEmptyProductList();
		}

		int productCount = productService.getProductsCountByCategoryId(categoryId);
		if (productCount == 0) {
			return getEmptyProductList();
		}

		ProductItems productItems = new ProductItems();
		productItems.setProductCount(productCount);
		productItems.setProductList(productService.getProducts(categoryId, start, limit));

		return productItems;
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

	private ProductItems getEmptyProductList() {
		ProductItems productItems = new ProductItems();
		productItems.setProductCount(0);
		productItems.setProductList(Collections.emptyList());

		return productItems;
	}
}
