/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.response.CommentResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
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
	public ProductResponseDto getProducts(
		@RequestParam(required = false, defaultValue = CATEGORY_TYPE_ALL) int categoryId,
		@RequestParam int start, @RequestParam(required = false, defaultValue = PRODUCTS_LIMIT) int limit) {
		return productService.getProductResponse(categoryId, start, limit);
	}

	/**
	 * 프로덕트 id에 해당하는 이미지들과 이미지 개수 그리고 해당 프로덕트의 description를 가져와 반환 
	 */
	@GetMapping("/{productId}/image")
	public Map<String, Object> getProductImageUrl(@PathVariable int productId,
		@RequestParam(required = false, defaultValue = IMAGE_TYPE_ETC) String type) {
		String productImageUrl = productService.getProductImage(productId, ImageType.valueOf(type)).getSaveFileName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productImageUrl", productImageUrl);
		return map;
	}

	/**
	 * 프로덕트 id에 해당하는 상품평들과 상품평 개수, 그리고 상품평 평균점수를 가져와 반환
	 */
	@GetMapping("/{productId}/comments")
	public CommentResponseDto getCommentsAndAverageScore(@PathVariable int productId, int start,
		@RequestParam(required = false, defaultValue = COMMENTS_LIMIT) int limit) {
		return productService.getCommentResponse(productId, start, limit);
	}
}
