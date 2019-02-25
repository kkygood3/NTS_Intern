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

import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.productdisplay.model.ProductDisplay;
import com.nts.reservation.productdisplay.service.ProductDisplayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductDisplayServiceTest {
	@Autowired
	private ProductDisplayService productDisplayService;

	@Test
	public void selectProductDisplayTest() {
		ProductDisplay productDisplay = productDisplayService.getProductDisplay(1);
		System.out.println(productDisplay);
	}

}
