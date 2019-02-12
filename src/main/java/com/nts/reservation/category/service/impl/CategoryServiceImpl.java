/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.category.dao.impl.CategoryDaoImpl;
import com.nts.reservation.category.dto.Category;
import com.nts.reservation.category.service.CategoryService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDaoImpl categoryDaoImpl;

	@Override
	public List<Category> getItems() {
		return categoryDaoImpl.selectCategories();
	}
}
