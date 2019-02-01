/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import com.nts.project3.dto.Promotion;

public interface PromotionService extends DefaultService {
	public List<Promotion> getPromotions();

	public int getCount();
}
