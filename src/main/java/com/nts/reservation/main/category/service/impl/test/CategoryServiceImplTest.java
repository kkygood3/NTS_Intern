/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.category.service.impl.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.main.category.dto.Category;
import com.nts.reservation.main.category.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CategoryServiceImplTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryService categoryService = ac.getBean(CategoryService.class);

		List<Category> categoryList = categoryService.getCategoryList();
	}

}
