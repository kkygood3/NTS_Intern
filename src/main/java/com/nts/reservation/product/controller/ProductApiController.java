/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.model.ProductListInfo;
import com.nts.reservation.product.model.ProductRequest;
import com.nts.reservation.product.model.ProductRequirer;
import com.nts.reservation.product.model.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
public class ProductApiController {

	@Autowired
	private ProductService productService;

	/**
	 * categoryId, start parameter로 product list와 count를 Json으로 응답
	 */
	@GetMapping(value = {"/api/products"})
	public ProductResponse getProductResponse(@Valid ProductRequest productRequest) {

		ProductRequirer productRequirer = new ProductRequirer(productRequest.getCategoryId(),
			productRequest.getStart());
		ProductListInfo productListInfo = productService.getProductListInfo(productRequirer);

		return new ProductResponse(productListInfo.getProductList(), productListInfo.getTotalCount());
	}
}