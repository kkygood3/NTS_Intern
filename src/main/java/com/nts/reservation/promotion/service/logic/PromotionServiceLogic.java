/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.common.annotation.IsEmpty;
import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.model.Promotion;
import com.nts.reservation.promotion.service.PromotionService;

@Service
public class PromotionServiceLogic implements PromotionService {

	private final PromotionDao promotionDao;

	@Autowired
	public PromotionServiceLogic(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}

	/**
	 * dao에서 promotion List 객체를 받아  PromotionResponse 객체로 생성 후 반환
	 */
	@Override
	@IsEmpty
	public List<Promotion> getPromotionList() {
		return promotionDao.selectPromotionList();
	}

}
