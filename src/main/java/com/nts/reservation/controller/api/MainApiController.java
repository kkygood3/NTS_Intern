/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.property.CommonProperties;
import com.nts.reservation.property.ProductProperties;
import com.nts.reservation.service.MainService;

@RestController
@RequestMapping("/api")
public class MainApiController {
	@Autowired
	private MainService mainResponseService;

	/**
	 * /api/categories 요청을 받아 메인 페이지에 카테고리 목록 출력
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return categoryList JSON text
	 */
	@GetMapping("/categories")
	public Map<String, Object> categories(
		@RequestParam(name = "pagingLimit", required = false, defaultValue = CommonProperties.CATEGORY_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		return Collections.singletonMap("mainCategoryReseponse", mainResponseService.getCategories(pagingLimit));
	}

	/**
	 * /api/products 요청을 받아 메인 페이지에 상품정보를 출력
	 * @param categoryId 해당 카테고리에 속하는 상품 요청. 0일때 카테고리 구분 없음
	 * @param start 페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return productList JSON text
	 */
	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = false, defaultValue = ProductProperties.PRODUCT_DEFAULT_CATEGORY_ID) Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = ProductProperties.PRODUCT_DEFAULT_START) Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = CommonProperties.PRODUCT_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {
		
		if (start < 0) {
			start = 0;
		}
		
		return Collections.singletonMap("mainProductReseponse", mainResponseService.getProducts(categoryId, start, pagingLimit));
	}

	/**
	 * /api/promotions 요청을 받아 메인 페이지에 프로모션 정보를 출력
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return promotionList JSON text
	 */
	@GetMapping("/promotions")
	public Map<String, Object> promotions(
		@RequestParam(name = "pagingLimit", required = false, defaultValue = CommonProperties.PROMOTION_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		return Collections.singletonMap("mainPromotionReseponse", mainResponseService.getPromotions(pagingLimit));
	}
}
