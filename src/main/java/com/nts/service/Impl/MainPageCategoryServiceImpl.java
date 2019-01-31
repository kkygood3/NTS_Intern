/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.MainPageCategoryDao;
import com.nts.dto.queries.MainPageCategoryQueries;
import com.nts.service.MainPageCategoryService;

@Service
public class MainPageCategoryServiceImpl implements MainPageCategoryService {

	@Autowired
	private MainPageCategoryDao categoryApiViewDao;

	@Override
	public List<MainPageCategoryQueries> getAllCategories() {
		return categoryApiViewDao.selectAllCategories();
	}
	
}
