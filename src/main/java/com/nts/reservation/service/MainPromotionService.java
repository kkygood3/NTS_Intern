/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.MainPromotion;

public interface MainPromotionService {
	/**
	 * @value	100 
	 */
	Integer LIMIT = 100;
	
	/**
	 * promotion_api_view를 조회
	 * @return default LIMIT만큼 조회하여 반환
	 */
	List<MainPromotion> getPromotions();

	/**
	 * promotion_api_view를 조회
	 * @return 해당 View의 row 수
	 */
	int getCount();
}
