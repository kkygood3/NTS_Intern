/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.promotion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.layer.promotion.dao.PromotionDao;
import com.nts.layer.promotion.dto.PromotionResponse;
import com.nts.layer.promotion.service.PromotionService;

/**
 * @author 전연빈
 */
@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	@Override
	public PromotionResponse getPromotionAll() {
		return new PromotionResponse(promotionDao.selectCategories());
	}

}
