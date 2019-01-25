/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.PromotionDao;
import com.nts.reservationservice.dto.PromotionDto;
import com.nts.reservationservice.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;

	@Override
	@Transactional
	public List<PromotionDto> getPromotions() {
		return promotionDao.selectPromotions();
	}

}
