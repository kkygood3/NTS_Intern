package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.Category;
import com.nts.dto.ProductApiView;
import com.nts.dto.Promotion;
import com.nts.service.CategoryService;
import com.nts.service.ProductApiViewService;
import com.nts.service.PromotionService;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
	@Autowired
	ProductApiViewService productApiViewService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	PromotionService promotionService;

	@GetMapping(path = "/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<ProductApiView> items = productApiViewService.getProducts(categoryId, start);
		int totalCount = productApiViewService.getCount(categoryId);

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}

	@GetMapping(path = "/categories")
	public Map<String, Object> categories() {

		List<Category> items = categoryService.getAllCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("items", items);

		return map;
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> promotions() {

		List<Promotion> items = promotionService.getAllPromotions();
		int totalCount = promotionService.getCount();

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}
}
