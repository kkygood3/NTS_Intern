/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.project3.dto.MainCategory;
import com.nts.project3.dto.MainProduct;
import com.nts.project3.dto.MainPromotion;
import com.nts.project3.service.MainCategoryService;
import com.nts.project3.service.MainProductService;
import com.nts.project3.service.MainPromotionService;
import com.nts.project3.service.impl.MainCategoryServiceImpl;
import com.nts.project3.service.impl.MainProductServiceImpl;
import com.nts.project3.service.impl.MainPromotionServiceImpl;

/**
 * /api로 request를 받아 클라이언트가 요구하는 정보를 JSON으로 출력하는 RestContorller
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private MainProductService mainProductService;
	@Autowired
	private MainCategoryService mainCategoryService;
	@Autowired
	private MainPromotionService mainPromotionService;
	
	/**
	 * /products 요청을 받아 메인 페이지에 상품정보를 출력
	 * @param	categoryId	해당 카테고리에 속하는 상품 요청. 0일때 카테고리 구분 없음
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	JSON text
	 */
	@GetMapping("/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start) {

		List<MainProduct> items = new ArrayList<>();
		int totalCount = 0;
		
		totalCount = mainProductService.getCount(categoryId);

		if (totalCount > 0) {
			items = mainProductService.getProducts(categoryId,start);
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

		List<MainCategory> items = mainCategoryService.getCategories();
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

		List<MainPromotion> items = mainPromotionService.getPromotions();
		int totalCount = mainPromotionService.getCount();

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}
}
