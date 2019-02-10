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
	 * api/promotions response에 사용
	 * @return default LIMIT만큼 조회하여 반환
	 */
	List<MainPromotion> getPromotions();

	/**
	 * api/promotions response에 사용
	 * @return 해당 View의 row 수
	 */
	int getCount();
}
