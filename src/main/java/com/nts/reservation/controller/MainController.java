package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.ProductService;

@Controller
public class MainController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(path = "/main")
	public String goMainPage(ModelMap model) {
		List<Product> productList = productService.getProductsByCategory(0, 0);
		int productCount = productService.getProductsCountByCategory(0);
		List<Category> categoryList = categoryService.getCategory();

		model.addAttribute("productList", productList);
		model.addAttribute("productCount", productCount);
		model.addAttribute("categoryList", categoryList);
		return "mainpage";
	}
}
