/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.category.model.Category;
import com.nts.reservation.category.model.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;
import com.nts.reservation.common.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void getCategoryListTest() {
		CategoryResponse categoryResponse = new CategoryResponse(categoryService.getCategoryList());

		for (Category category : categoryResponse.getCategoryList()) {
			System.out.println(category);
		}
	}
}
