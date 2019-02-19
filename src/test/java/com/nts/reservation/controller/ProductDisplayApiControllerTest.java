/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.productdisplay.controller.ProductDisplayApiController;
import com.nts.reservation.productdisplay.model.ProductDisplayResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductDisplayApiControllerTest {

	@Autowired
	private ProductDisplayApiController productDisplayApiController;

	@Test
	public void getProductDisplayResponseTest() {
		ProductDisplayResponse productDisplayResponse = productDisplayApiController.getProductDisplayResponse(1);

		System.out.println(productDisplayResponse);
	}
}
