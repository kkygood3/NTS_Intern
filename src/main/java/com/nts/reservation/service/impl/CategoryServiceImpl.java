/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategoryList() {
		return categoryDao.selectCategories(LIMIT);
	}
}
