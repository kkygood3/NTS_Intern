package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.PromotionService;

@Controller
public class MainController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PromotionService promotionService;

	@GetMapping(path = "/main")
	public String goMainPage(ModelMap model) {
		// TODO 전부 AJAX 처리
		List<Product> productList = productService.getProductsByCategory(0, 0);
		int productCount = productService.getProductsCountByCategory(0);
		List<Category> categoryList = categoryService.getCategory();
		
		List<Promotion> promotionList = promotionService.getPromotions();

		model.addAttribute("productList", productList);
		model.addAttribute("productCount", productCount);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("promotionList", promotionList);
		return "mainpage";
	}
}
