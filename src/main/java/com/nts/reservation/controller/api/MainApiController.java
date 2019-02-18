/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.main.MainCategory;
import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.dto.main.MainPromotion;
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

		List<MainCategory> categoryList = mainResponseService.getCategories(pagingLimit);
		
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);

		return map;
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

		List<MainProduct> productList = new ArrayList<>();
		int totalCount = mainResponseService.getProductCount(categoryId);

		if (totalCount > 0) {
			productList = mainResponseService.getProducts(categoryId, start, pagingLimit);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("totalCount", totalCount);

		return map;
	}

	/**
	 * /api/promotions 요청을 받아 메인 페이지에 프로모션 정보를 출력
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return promotionList JSON text
	 */
	@GetMapping("/promotions")
	public Map<String, Object> promotions(
		@RequestParam(name = "pagingLimit", required = false, defaultValue = CommonProperties.PROMOTION_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<MainPromotion> promotionList = new ArrayList<>();
		int totalCount = mainResponseService.getPromotionCount();

		if (totalCount > 0) {
			promotionList = mainResponseService.getPromotions(pagingLimit);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("promotionList", promotionList);
		map.put("totalCount", totalCount);

		return map;
	}

	/**
	 * queryForObject 요청의 결과값이 없을때 발생하는 예외 처리
	 * @return emptyMap
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public Map<String, Object> handleEmptyResult() {
		return Collections.emptyMap();
	}

}
