/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.model.Product;
import com.nts.reservation.product.model.ProductRequest;
import com.nts.reservation.product.model.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
public class ProductApiController {

	private final ProductService productService;

	@Autowired
	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * categoryId, start parameter로 product list와 count를 Json으로 응답
	 */
	@GetMapping(value = {"/api/products"})
	public ProductResponse getProductResponse(@Validated ProductRequest productRequest) {

		List<Product> productList = productService.getProductList(productRequest.getCategoryId(),
			productRequest.getStart());
		int productCount = productService.getProductCount(productRequest.getCategoryId());

		return new ProductResponse(productList, productCount);
	}
}