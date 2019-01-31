/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.Promotion;

public interface PromotionService {
	public List<Promotion> getAllPromotions();
	
	public int getCount();
}
