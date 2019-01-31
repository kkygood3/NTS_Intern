/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.response.CategoryResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.PromotionResponseDto;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.ProductService;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class MainPageApiController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	/**
	 * @desc 카테고리 별 프로덕트 리스트 요청하는 Api
	 * @param start - 요구하는 상품의 시작 순번
	 * @param categoryId - 요청하는 카테고리의 id
	 * @return ProductResponseDto(items [프로덕트 리스트] , totalCount [카테고리 별 총 갯수 ] )
	 */
	@GetMapping("/products")
	public ProductResponseDto getProductResponse(
		@RequestParam(name = "start", defaultValue = "0") Integer start,
		@RequestParam(name = "categoryId") Integer categoryId) {

		List<ProductDto> items = Collections.emptyList();
		int count = 0;

		if (categoryId == null) {
			items = productService.getProductList(start);
			count = productService.getCount();
		} else {
			items = productService.getProductList(start, categoryId);
			count = productService.getCount(categoryId);
		}

		return new ProductResponseDto(items, count);
	}

	/**
	 * @desc 프로모션 리스트를 요청하는 Api
	 * @return PromotionResponseDto(items [프로모션 리스트] , totalCount [프로모션 갯수] )
	 */
	@GetMapping("/promotions")
	public PromotionResponseDto getPromotionResponse() {

		List<ProductDto> items = productService.getPromotionProductList();
		return new PromotionResponseDto(items, items.size());
	}

	/**
	 * @desc 카테고리 정보를 요청하는 Api
	 * @return CategoryResponseDto(items [카테고리 리스트])
	 */
	@GetMapping("/categories")
	public CategoryResponseDto getCategoryResponse() {

		List<CategoryDto> items = categoryService.getCategoryList();
		return new CategoryResponseDto(items);
	}
}
