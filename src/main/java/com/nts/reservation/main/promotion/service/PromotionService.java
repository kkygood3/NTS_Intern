/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.promotion.service;

import java.util.List;

import com.nts.reservation.main.promotion.dto.Promotion;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface PromotionService {
	public static final int PROMOTION_LIMIT = 100;

	List<Promotion> getPromotions();

	int getPromotionsCount();
}
