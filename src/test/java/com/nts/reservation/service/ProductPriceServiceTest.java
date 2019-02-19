/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.productdisplayprice.model.ProductPrice;
import com.nts.reservation.productdisplayprice.service.ProductPriceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductPriceServiceTest {

	@Autowired
	private ProductPriceService productPriceService;

	@Test
	public void getProductPriceTest() {

		List<ProductPrice> productPriceList = productPriceService.getProductPriceListByDisplayInfoId(40);

		for (ProductPrice productPrice : productPriceList) {
			System.out.println(productPrice);
		}
	}

}
