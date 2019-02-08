package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path = "/categories")
	public CategoryResponse getCategory(){
		return categoryService.getCategories();
	}
}
