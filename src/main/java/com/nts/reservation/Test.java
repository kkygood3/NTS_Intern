/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.service.ProductService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		//		CategoryDao dao = ac.getBean(CategoryDao.class);

		ProductService service = ac.getBean(ProductService.class);

		//		List<ProductDto> list = service.getProducts(10);
		List<ProductDto> list = service.getProducts(0, 2);
		//		List<ProductDto> list = service.getCount();
		//		List<ProductDto> list = service.getCount(categoryId);

		for (ProductDto item : list) {
			System.out.println(item.toString());
		}
		System.out.println("count : " + service.getCount() + " count : " + service.getCount(3));
		//		List<CategoryDto> list = dao.selectCategory();

		//		for (CategoryDto item : list) {
		//			System.out.println(item.toString());
		//		}
	}

}
