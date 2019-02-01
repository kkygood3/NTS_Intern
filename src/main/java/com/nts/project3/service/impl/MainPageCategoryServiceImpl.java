/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.MainPageCategoryDao;
import com.nts.project3.dto.MainPageCategory;
import com.nts.project3.service.MainPageCategoryService;

@Service
public class MainPageCategoryServiceImpl implements MainPageCategoryService {

	@Autowired
	private MainPageCategoryDao mainPageCategoryDao;

	@Override
	public List<MainPageCategory> getCategories() {
		return mainPageCategoryDao.selectCategories(LIMIT);
	}

}
