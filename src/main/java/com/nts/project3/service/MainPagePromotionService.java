/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import com.nts.project3.dto.MainPagePromotion;

public interface MainPagePromotionService extends DefaultService {
	/**
	 * promotion_api_view를 조회
	 * @return default LIMIT만큼 조회하여 반환
	 */
	public List<MainPagePromotion> getPromotions();

	/**
	 * promotion_api_view를 조회
	 * @return 해당 View의 row 수
	 */
	public int getCount();
}
