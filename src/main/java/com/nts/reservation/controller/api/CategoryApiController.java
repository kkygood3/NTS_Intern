package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.main.MainCategory;
import com.nts.reservation.service.main.MainCategoryService;

@RestController
public class CategoryApiController {
	@Autowired
	private MainCategoryService mainCategoryService;

	/**
	 * /api/categories 요청을 받아 메인 페이지에 카테고리 목록 출력
	 * @return	JSON text
	 */
	@GetMapping("/api/categories")
	public Map<String, Object> categories() {

		List<MainCategory> categoryList = mainCategoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);

		return map;
	}
}
