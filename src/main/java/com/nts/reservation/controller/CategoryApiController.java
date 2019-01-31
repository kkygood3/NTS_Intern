package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Category> list() {
		List<Category> CategoryList = categoryService.getCategoris();
		return CategoryList;
	}

}
