/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.service.CategoryService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	private final CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * @desc 카테고리 리스트를 반환한다.
	 */
	@Override
	public List<CategoryDto> getCategoryList() {
		List<CategoryDto> list = categoryDao.selectCategory();
		return list;
	}

}
