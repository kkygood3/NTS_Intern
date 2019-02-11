/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.category.dao.MainCategoryDao;
import com.nts.reservation.category.dto.MainCategory;
import com.nts.reservation.category.service.MainCategoryService;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;

	@Override
	public List<MainCategory> getCategories() {
		return mainCategoryDao.selectPagingCategories();
	}

}
