package com.nts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.dto.Product;
import com.nts.service.ProductService;

@Controller
public class MainPageController {

	@Autowired
	ProductService productService;

	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model) {

		List<Product> list = productService.getProducts(start);

		int count = productService.getCount();
		int pageCount = count / ProductService.LIMIT;
		if (count % ProductService.LIMIT > 0)
			pageCount++;

		model.addAttribute("list", list);
		model.addAttribute("count", count);

		return "mainpage";
	}
}
