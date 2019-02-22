/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.annotation.PageDefault;
import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리에 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 모든 카테고리 정보들을 json형태로 반환 
	 */
	@GetMapping
	public Map<String, Object> getCategoriesWithProductsOnDisplayCount(
		@PageDefault(limit = CATEGORIES_LIMIT) PageDto page) {
		List<CategoryDto> categories = categoryService.getCategories(page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categories", categories);

		return map;
	}
}
