/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.PromotionDao;
import com.nts.dto.Promotion;
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
public class PromotionServiceImpl implements PromotionService{
	@Autowired
	PromotionDao promotionDao;
	
	@Override
	@Transactional
	public List<Promotion> getItems(){
		return promotionDao.selectAll();
	}
}

