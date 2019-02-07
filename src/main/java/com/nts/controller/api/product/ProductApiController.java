/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nts.dto.productdto.Product;
import com.nts.dto.productdto.ProductResponse;
import com.nts.exception.ValidationException;
import com.nts.service.ProductService;

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
	private ProductService productService;

	/**
	 * @throws ValidationException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping
	public ProductResponse products(@RequestParam(name = "categoryId") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start)
			throws ValidationException, IllegalStateException, IOException {

		if (categoryId < 0) {
			throw new ValidationException("categoryId : " + categoryId);
		}
		if (start < 0) {
			throw new ValidationException("start : " + start);
		}
		
		
		List<Product> items = productService.getItems(categoryId, start);
		int totalCount = productService.getCount(categoryId);
		
		ProductResponse productResponse = new ProductResponse(items, totalCount);

		return productResponse;
	}
}