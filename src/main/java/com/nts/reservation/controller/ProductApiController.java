package com.nts.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		List<Product> ProductList = getProductList(start, categoryId);
		Map<String, Object> map = new HashMap<>();

		map.put("product_list", ProductList);
		return map;
	}

	private List<Product> getProductList(int start, int categoryId) {
		List<Product> ProductList = null;
		if (categoryId == 0) {
			ProductList = productService.getProducts(start);
		} else {
			ProductList = productService.getProductsByCategory(start, categoryId);
		}
		return ProductList;
	}
}
