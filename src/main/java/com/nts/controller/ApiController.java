/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.MainPageCategory;
import com.nts.dto.MainPageProduct;
import com.nts.dto.MainPagePromotion;
import com.nts.service.MainPageCategoryService;
import com.nts.service.MainPageProductService;
import com.nts.service.MainPagePromotionService;

/**
 * /api로 request를 받아 클라이언트가 요구하는 정보를 JSON으로 출력하는 RestContorller
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private MainPageProductService mainPageProductService;
	@Autowired
	private MainPageCategoryService mainPageCategoryService;
	@Autowired
	private MainPagePromotionService mainPagePromotionService;

	/**
	 * /products 요청을 받아 메인 페이지에 상품정보를 출력
	 * @param	categoryId	해당 카테고리에 속하는 상품 요청. 0일때 카테고리 구분 없음
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	JSON text
	 */
	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<MainPageProduct> items = new ArrayList<>();
		int totalCount = 0;

		//0일 때는 카테고리 구분 없음 
		if (categoryId > 0) {
			totalCount = mainPageProductService.getCountByCategory(categoryId);

			if (totalCount > 0) {
				items = mainPageProductService.getProductsByCategory(categoryId, start);
			}
		} else {
			totalCount = mainPageProductService.getCount();

			if (totalCount > 0) {
				items = mainPageProductService.getProducts(start);
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}

	/**
	 * /categories 요청을 받아 메인 페이지에 카테고리 목록 출력
	 * @return	JSON text
	 */
	@GetMapping("/categories")
	public Map<String, Object> categories() {

		List<MainPageCategory> items = mainPageCategoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("items", items);

		return map;
	}

	/**
	 * /promotions 요청을 받아 메인 페이지에 프로모션 정보를 출력
	 * @return	JSON text
	 */
	@GetMapping("/promotions")
	public Map<String, Object> promotions() {

		List<MainPagePromotion> items = mainPagePromotionService.getPromotions();
		int totalCount = mainPagePromotionService.getCount();

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}
}
