/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.config.ApplicationConfig;
import com.nts.dto.CategoryResponse;
import com.nts.service.CategoryService;

/**
 *
 * @description : Category API Controller
 * @package : com.nts.controller.api.category
 * @filename : CategoryApiController.java
 * @author : 최석현
 * @method : CategoryResponse categories()
 * 
 */
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;

	/**
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping
	public CategoryResponse categories() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		
		CategoryResponse categoryResponse = ac.getBean(CategoryResponse.class);
		categoryResponse.setItems(categoryService.getItems());
		
		return categoryResponse;
	}
}