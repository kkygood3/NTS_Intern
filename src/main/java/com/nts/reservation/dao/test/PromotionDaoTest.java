/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.Promotion;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class PromotionDaoTest {
	private static final long PROMOTION_LIMIT = 100;

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PromotionDao promotionDao = ac.getBean(PromotionDao.class);

		System.out.println(promotionDao.selectPromotionCount());

		List<Promotion> listPromotion = promotionDao.selectPromotions(PROMOTION_LIMIT);
		for (Promotion promotion : listPromotion) {
			System.out.println(promotion.toString());
		}
	}
}
