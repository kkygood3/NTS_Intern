/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.main.MainCategory;
import com.nts.reservation.property.DefaultPagingLimitProperties;
import com.nts.reservation.service.main.MainCategoryService;

@RestController
public class CategoryApiController {
	@Autowired
	private MainCategoryService mainCategoryService;

	/**
	 * /api/categories 요청을 받아 메인 페이지에 카테고리 목록 출력
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return categoryList JSON text
	 */
	@GetMapping("/api/categories")
	public Map<String, Object> categories(
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DefaultPagingLimitProperties.CATEGORY_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<MainCategory> categoryList = mainCategoryService.getCategories(pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);

		return map;
	}
}
