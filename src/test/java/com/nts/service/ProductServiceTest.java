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
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductRequest;
import com.nts.reservation.model.ProductResponse;
import com.nts.reservation.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	@Test
	public void selectTest() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCategoryId(0);
		productRequest.setStart(-1);
		ProductResponse productResponse = productService.getProductResponse(productRequest);

		System.out.println(productResponse.getTotalCount());
		for (Product product : productResponse.getItem()) {
			System.out.println(product);
		}
	}

}
