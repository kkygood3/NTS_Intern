/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.product.model.Product;
import com.nts.reservation.product.model.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	@Test
	public void selectTest() {

		List<Product> productList = productService.getProductList(3, 0);
		int productCount = productService.getProductCount(3);

		ProductResponse productResponse = new ProductResponse(productList, productCount);

		System.out.println(productResponse.getTotalCount());
		for (Product product : productResponse.getProductList()) {
			System.out.println(product);
		}
	}

}
