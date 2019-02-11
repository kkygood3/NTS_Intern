/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.category.dao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.main.category.dao.CategoryDao;
import com.nts.reservation.main.category.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CategoryDaoTest {

	private static final int CATEGORY_LIMIT = 10;

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao categoryDao = ac.getBean(CategoryDao.class);

		List<Category> listCategory = categoryDao.selectCategories(CATEGORY_LIMIT);
		for (Category category : listCategory) {
			System.out.println(category.toString());
		}
	}
}
