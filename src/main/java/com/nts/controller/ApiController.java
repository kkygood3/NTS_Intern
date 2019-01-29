package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.CategoryApiView;
import com.nts.dto.ProductApiView;
import com.nts.dto.PromotionApiView;
import com.nts.service.CategoryApiViewService;
import com.nts.service.ProductApiViewService;
import com.nts.service.PromotionApiViewService;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
	@Autowired
	ProductApiViewService productApiViewService;
	@Autowired
	CategoryApiViewService categoryApiViewService;
	@Autowired
	PromotionApiViewService promotionApiViewService;

	@GetMapping(path = "/products")
	public Map<String, Object> products(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<ProductApiView> items;
		items = productApiViewService.getProducts(categoryId, start);
		
		int totalCount = productApiViewService.getCount(categoryId);

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}

	@GetMapping(path = "/categories")
	public Map<String, Object> categories() {

		List<CategoryApiView> items = categoryApiViewService.getAllCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("items", items);

		return map;
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> promotions() {

		List<PromotionApiView> items = promotionApiViewService.getAllPromotions();
		int totalCount = promotionApiViewService.getCount();

		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);

		return map;
	}
}
