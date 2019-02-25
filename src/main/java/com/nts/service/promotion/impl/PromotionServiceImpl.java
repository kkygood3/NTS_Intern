/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.promotion.impl;

import org.springframework.stereotype.Service;

import com.nts.dao.promotion.PromotionRepository;
import com.nts.dto.promotion.Promotions;
import com.nts.service.promotion.PromotionService;

/**
 * @author 전연빈
 */
@Service
public class PromotionServiceImpl implements PromotionService {

	private final PromotionRepository promotionRepository;

	public PromotionServiceImpl(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	/**
	 * @desc 프로모션 전체 불러오기
	 * @return promotions { items: promotionList }
	 */
	@Override
	public Promotions getPromotions() {
		return new Promotions(promotionRepository.selectPromotions());
	}

}
