/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.promotion.service;

import java.util.List;

import com.nts.reservation.promotion.dto.MainPromotion;

public interface MainPromotionService {
	public List<MainPromotion> getPromotions();
}
