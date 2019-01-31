/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.PromotionApiViewDao;
import com.nts.dto.PromotionApiView;
import com.nts.service.PromotionApiViewService;

@Service
public class PromotionApiViewServiceImpl implements PromotionApiViewService {

	@Autowired
	private PromotionApiViewDao promotionApiViewDao;

	@Override
	public List<PromotionApiView> getAllPromotions() {
		return promotionApiViewDao.selectAllPromotions();
	}

	@Override
	public int getCount() {
		return promotionApiViewDao.selectCount();
	}

}
