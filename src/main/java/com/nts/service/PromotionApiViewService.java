/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.PromotionApiView;

public interface PromotionApiViewService {
	public List<PromotionApiView> getAllPromotions();

	public int getCount();
}
