/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ProductDaoTest {
	//	private static final int categoryId = 1;
	private static final int CATEGORY_LIMIT = 100;

	private static final int PRODUCT_START = 0;
	private static final int PRODUCT_LIMIT = 4;

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CategoryDao categoryDao = ac.getBean(CategoryDao.class);
		List<Category> listCategory = categoryDao.selectCategory(CATEGORY_LIMIT);

		ProductDao productDao = ac.getBean(ProductDao.class);

		for (Category category : listCategory) {
			System.out.println(productDao.selectProductCountByCategory(category.getId()));

			List<Product> listProduct = productDao.selectProductByCategory(category.getId(), PRODUCT_START,
				PRODUCT_LIMIT);
			for (Product product : listProduct) {
				System.out.println(product.toString());
				System.out.println("");
			}
			System.out.println("//");
		}

	}
}
