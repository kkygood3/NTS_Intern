/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.MainCategoryDao;
import com.nts.project3.dto.MainCategory;
import com.nts.project3.service.MainCategoryService;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;

	@Override
	public List<MainCategory> getCategories() {
		return mainCategoryDao.selectCategories(LIMIT);
	}

}
