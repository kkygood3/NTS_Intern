/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.model.Category;
import com.nts.reservation.model.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void getCategoryListTest() {
		CategoryResponse categoryResponse = categoryService.getCategoryResponse();

		for (Category category : categoryResponse.getItem()) {
			System.out.println(category);
		}
	}
}
