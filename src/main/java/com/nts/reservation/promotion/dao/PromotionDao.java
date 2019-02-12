/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao;

import java.util.List;

import com.nts.reservation.promotion.dto.Promotion;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface PromotionDao {
	List<Promotion> selectPromotions(long limit);

	int selectPromotionsCount();
}
