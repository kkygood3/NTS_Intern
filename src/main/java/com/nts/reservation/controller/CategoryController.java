package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public CategoryResponse getCategory(){
		return categoryService.getCategories();
	}
}
