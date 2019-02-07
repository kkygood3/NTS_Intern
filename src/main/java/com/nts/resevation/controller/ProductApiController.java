/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.resevation.dto.ProductResponseDto;
import com.nts.resevation.service.ProductService;

/**
 * 상품 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	/**
	 * 전시상품들을 start번째부터 최대 limit 수만큼 리스트로 가져오고
	 * 전시되고있는 모든상품들의 총 개수를 json으로 리턴해준다. 
	 */
	@GetMapping("/api/products")
	public ProductResponseDto getProductResponse(@RequestParam int start) {
		return productService.getProductResponse(start);
	}

	/**
	 * 해당카테고리의 전시상품들을 start번째부터 최대 limit 수만큼 리스트로 가져오고 
	 * 해당카테고리의 전시상품들의 총개수를 가져와 json으로 리턴해준다 
	 */
	@GetMapping("/api/categories/{categoryId}/products")
	public ProductResponseDto getProductsByCategoryId(@PathVariable int categoryId, @RequestParam int start) {
		return productService.getProductResponse(categoryId, start);
	}
}
