/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.Product;
import com.nts.dto.ProductResponse;
import com.nts.service.ProductService;

import com.nts.config.ApplicationConfig;


/**
 *
 * @description : Product API Controller
 * @package : com.nts.controller.api.product
 * @filename : ProductApiController.java
 * @author : 최석현
 * @method : ProductResponse products(int categoryId, int start)
 * 
 */
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	/**
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping
	public ProductResponse products(@RequestParam(name="categoryId" ) int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		List<Product> items = productService.getItems(categoryId, start);
		int totalCount = productService.getCount(categoryId);

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		ProductResponse productResponse = ac.getBean(ProductResponse.class);
		productResponse.setItems(items);
		productResponse.setTotalCount(totalCount);
		
		return productResponse;
	}
}