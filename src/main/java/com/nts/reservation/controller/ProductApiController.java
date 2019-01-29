package com.nts.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping("/products/{start}/{category}")
	public Map<String, Object> productByCategory(@PathVariable Integer start, @PathVariable Integer category) {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getProducts(start, category));
		result.put("totalCount", productService.getProductsCount());
		return result;
	}

	@GetMapping("/categories")
	public Map<String, Object> getCountsByCategory() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getProductsCountByCategory());
		return result;
	}

	@GetMapping("/promotions")
	public Map<String, Object> promotionList() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productService.getPromotions());
		return result;
	}

	@GetMapping("/promotions/{displayInfoId}")
	public Map<String, Object> promotionList(@PathVariable int displayInfoId) {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

}