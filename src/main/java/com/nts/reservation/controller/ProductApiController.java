/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.response.CategoryResponseDto;
import com.nts.reservation.dto.response.DisplayInfoResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.PromotionResponseDto;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DisplayInfoService;
import com.nts.reservation.service.ProductService;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class ProductApiController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayInfoService displayInfoService;

	/**
	 * @desc 카테고리 별 프로덕트 리스트 요청하는 Api
	 * @param start
	 * @param categoryId
	 * @return ProductResponseDto(items [프로덕트 리스트] , totalCount [카테고리 별 총 갯수 ] )
	 */
	@GetMapping("/products")
	public ProductResponseDto getProductResponse(
		@RequestParam(name = "start", defaultValue = "0", required = false) Integer start,
		@RequestParam(name = "categoryId", required = false) Integer categoryId) {

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

	/**
	 * @desc 상품에 대한 상세 정보를 요청하는 Api
	 * @param displayInfoId
	 * @return DisplayInfoResponseDto (averageScore [평균 점수], comments [댓글 리스트] displayInfo [상세정보],
	 * 						 displayInfoImage [상세 정보 이미지], productImages [상품이미지 리스트], productPrices [가격정보 리스트])
	 */
	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoResponseDto getDisplayInfoResponse(@PathVariable Long displayInfoId) {

		DisplayInfoDto displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImageDto displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);
		List<ProductImageDto> productImages = displayInfoService.getProductImageList(displayInfo.getProductId());
		List<ProductPriceDto> productPrices = displayInfoService.getProductPriceList(displayInfo.getProductId());
		List<CommentDto> comments = displayInfoService.getCommentList(displayInfoId);
		float averageScore = displayInfoService.getCommentAvgScore(displayInfoId);

		return new DisplayInfoResponseDto(averageScore, comments, displayInfo, displayInfoImage, productImages,
			productPrices);
	}
}
