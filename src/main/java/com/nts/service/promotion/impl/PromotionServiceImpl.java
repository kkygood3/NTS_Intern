/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.promotion.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.promotion.PromotionDao;
import com.nts.dto.promotion.PromotionResponse;
import com.nts.service.promotion.PromotionService;

/**
 * @author 전연빈
 */
@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	/**
	 * @desc 프로모션 전체 불러오기
	 * @return promotionResponse { items: promotionList }
	 */
	@Override
	public PromotionResponse getPromotionAll() {
		return new PromotionResponse(promotionDao.selectCategories());
	}

}
