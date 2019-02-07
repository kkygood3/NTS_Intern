/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.model.Promotion;
import com.nts.reservation.service.PromotionService;

@Service
public class PromotionServiceLogic implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	/**
	 * dao에서 promotion List 객체를 받아  PromotionResponse 객체로 생성 후 반환
	 */
	@Override
	public List<Promotion> getPromotionList() {
		return promotionDao.getPromotionList();
	}

}
