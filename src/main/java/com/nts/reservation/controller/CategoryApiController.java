package com.nts.reservation.controller;

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

import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		List<Category> CategoryList = categoryService.getCategoris();
		Map<String, Object> map = new HashMap<>();
		map.put("list", CategoryList);
		return map;
	}

}
