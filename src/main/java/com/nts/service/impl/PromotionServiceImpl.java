/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.promotiondao.PromotionDao;
import com.nts.dto.promotiondto.Promotion;
import com.nts.service.PromotionService;

/**
 *
 * @description : Promotion Service
 * @package : com.nts.service.impl
 * @filename : PromotionServiceImpl.java
 * @author : 최석현
 * 
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionDao promotionDao;

	@Override
	public List<Promotion> getItems() {
		return promotionDao.selectPromotionsAll();
	}
}
