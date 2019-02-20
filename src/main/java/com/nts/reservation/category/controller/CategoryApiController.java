package com.nts.reservation.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;

@RestController
@RequestMapping(path = "/api")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/categories")
	public CategoryResponse getCategory() {
		return categoryService.getCategories();
	}
}
