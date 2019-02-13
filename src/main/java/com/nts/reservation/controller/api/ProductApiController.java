/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CommentResponseDto;
import com.nts.reservation.dto.ProductImageResponseDto;
import com.nts.reservation.dto.ProductResponseDto;
import com.nts.reservation.service.ProductService;

/**
 * 상품 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	/**
	 * 전시상품들을 start번째부터 최대 limit 수만큼 리스트로 가져오고
	 * 전시되고있는 상품들의 총 개수를 json으로 리턴해준다. 
	 */
	@GetMapping
	public ProductResponseDto getProductResponse(
		@RequestParam(required = false, defaultValue = CATEGORY_TYPE_ALL) int categoryId,
		@RequestParam int start, @RequestParam(required = false, defaultValue = PRODUCTS_LIMIT) int limit) {
		return productService.getProductResponse(categoryId, start, limit);
	}

	@GetMapping("/{productId}/images")
	public ProductImageResponseDto getProductImageResponse(@PathVariable int productId,
		@RequestParam(required = false, defaultValue = PRODUCT_IMAGE_LIMIT) int limit) {
		return productService.getProductImageResponse(productId, limit);
	}

	@GetMapping("/{productId}/comments")
	public CommentResponseDto getCommentResponse(@PathVariable int productId, int start,
		@RequestParam(required = false, defaultValue = COMMENTS_LIMIT) int limit) {
		return productService.getCommentResponse(productId, start, limit);
	}
}
