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

import com.nts.reservation.commons.validator.NegativeValueValidator;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	/**
	 * /products API요청시 totalCount와 product 관련 정보 json 전송
	 *@author kkygood3
	 */
	@GetMapping("/products")
	public ProductResponse products(
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "ProductUnits", required = false, defaultValue = "4") int ProductUnits) {

		if(NegativeValueValidator.isNegativeValue(start, categoryId)) {
			throw new IllegalArgumentException("start : " + start + ", cateegoryId : " + categoryId + ", ProductUnits : " + ProductUnits);
		}

		return productServiceImpl.getProducts(categoryId, start, ProductUnits);
	}

}
