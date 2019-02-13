/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.category.dao.impl.CategoryDaoImpl;
import com.nts.reservation.category.dto.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDaoImpl categoryDaoImpl;

	@Override
	public CategoryResponse getCategories() {
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setItems(categoryDaoImpl.selectPagingCategories());

		return categoryResponse;
	}

}
