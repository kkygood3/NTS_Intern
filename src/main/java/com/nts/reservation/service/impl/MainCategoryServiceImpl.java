/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.MainCategoryDao;
import com.nts.reservation.dto.MainCategory;
import com.nts.reservation.service.MainCategoryService;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;

	@Override
	public List<MainCategory> getCategories() {
		return mainCategoryDao.selectCategories(LIMIT);
	}

}
