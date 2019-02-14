/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.display.dto.DisplayResponse;
import com.nts.reservation.display.service.DisplayService;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

	// TODO 어짜피 싱글톤이면 autowired를 한곳에 쳐박아두면 안되나?

	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayService displayService;

	// 한번에 많은 양의 db조회를 막기 위함
	private static final int MAX_LIMIT = 20;

	@GetMapping(path = "/products")
	public ProductResponse getProducts(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (limit > MAX_LIMIT) {
			limit = MAX_LIMIT;
		}

		if (start < 0) {
			return ProductResponse.builder().items(Collections.emptyList()).build();
		}

		return productService.getProductsByCategory(categoryId, start, limit);
	}

	@GetMapping(path = "/products/{displayInfoId}")
	public DisplayResponse getDisplay(@PathVariable("displayInfoId") int displayInfoId) {
		if (displayInfoId < 0) {
			throw new IllegalArgumentException();
		}

		return displayService.getDisplayInfo(displayInfoId);
	}
	
	@GetMapping(path = "/products/etc/{displayInfoId}")
	public ProductImage getProductEtcImage(@PathVariable("displayInfoId") int displayInfoId) {
		// TODO LIST -> ProductImage
		if(productService.getProductImages(displayInfoId, "et").size() == 0) {
			return new ProductImage();
		}
		return productService.getProductImages(displayInfoId, "et").get(0);
	}
}
