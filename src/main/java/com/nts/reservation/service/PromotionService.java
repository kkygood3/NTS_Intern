/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Promotion;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface PromotionService {
	Integer LIMIT = 100;

	List<Promotion> getPromotions();

	int getCount();
}
