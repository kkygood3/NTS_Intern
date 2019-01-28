package com.nts.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public Map<String, Object> productByCategory() {
		Map<String, Object> map = new HashMap<>();
		List<Product> list = productService.getProducts();
		map.put("items", list);
		map.put("totalCount", list.size());
		return map;
	}

	@GetMapping("/promotions")
	public Map<String, Object> promotionList() {
		Map<String, Object> map = new HashMap<>();
		map.put("items", productService.getPromotions());
		return map;
	}

	@GetMapping("/promotions/{displayInfoId}")
	public Map<String, Object> promotionList(@PathVariable int displayInfoId) {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

	@GetMapping("/categories")
	public Map<String, List<Promotion>> categoryList() {
		return null;
	}

}