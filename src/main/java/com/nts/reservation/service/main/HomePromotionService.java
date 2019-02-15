/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.main;

import java.util.List;

import com.nts.reservation.dto.main.HomePromotion;

public interface HomePromotionService {
	/**
	 * api/promotions response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return default LIMIT만큼 조회하여 반환
	 */
	List<HomePromotion> getPromotions(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 * @return 해당 View의 row 수
	 */
	int getCount();
}
