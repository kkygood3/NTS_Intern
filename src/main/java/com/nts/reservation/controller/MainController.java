/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@Controller
public class MainController {
	@Autowired
	ProductService productService;

	@GetMapping(path = "/")
	public String countItem(ModelMap model) {
		int count = productService.getCount();
		model.addAttribute("countItem", count);
		return "mainpage";
	}

	@PostMapping(path = "/category")
	public String tempCategoryChange(@RequestParam("id") int categoryId) {
		List<Product> products = productService.getProduct(categoryId);
		System.out.println(products.size());
		return "mainpage";
	}
}
