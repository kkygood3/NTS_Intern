/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.PromotionDao;
import com.nts.dto.PromotionDto;

/**
 * 프로모션에 대한 서비스 로직
 * @author jinwoo.bae
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionDao promotionDao;

	@Override
	public List<PromotionDto> getPromotions() {
		return promotionDao.selectAll();
	}

}
