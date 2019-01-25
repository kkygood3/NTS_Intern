/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.model.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@Service
public class CategoryServiceLogic implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public CategoryResponse getCategoryResponse() {
		return new CategoryResponse(categoryDao.getCategoryList());
	}

}
