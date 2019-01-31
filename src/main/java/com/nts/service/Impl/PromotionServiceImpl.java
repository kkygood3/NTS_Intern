/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.PromotionDao;
import com.nts.dto.Promotion;
import com.nts.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	public List<Promotion> getPromotions() {
		return promotionDao.selectPromotions(LIMIT);
	}
	
	@Override
	public int getCount() {
		return promotionDao.selectCount();
	}
}
