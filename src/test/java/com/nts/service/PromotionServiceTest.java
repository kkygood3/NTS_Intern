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
import com.nts.reservation.model.Promotion;
import com.nts.reservation.model.PromotionResponse;
import com.nts.reservation.service.PromotionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class PromotionServiceTest {

	@Autowired
	private PromotionService promotionService;

	@Test
	public void getPromotionList() {
		PromotionResponse promotionResponse = new PromotionResponse(promotionService.getPromotionList());

		for (Promotion promotion : promotionResponse.getItem()) {
			System.out.println(promotion);
		}
	}

}
