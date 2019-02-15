/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.HomePromotionDao;
import com.nts.reservation.dto.main.HomePromotion;
import com.nts.reservation.service.main.HomePromotionService;

@Service
public class HomePromotionServiceImpl implements HomePromotionService {

	@Autowired
	private HomePromotionDao homePromotionDao;

	@Override
	public List<HomePromotion> getPromotions(int pagingLimit) {
		return homePromotionDao.selectPromotions(pagingLimit);
	}

	@Override
	public int getCount() {
		return homePromotionDao.selectCount();
	}

}
