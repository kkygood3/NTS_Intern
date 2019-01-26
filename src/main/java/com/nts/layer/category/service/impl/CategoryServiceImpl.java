/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.layer.category.dao.CategoryDao;
import com.nts.layer.category.dto.CategoryResponse;
import com.nts.layer.category.service.CategoryService;

/**
 * @author 전연빈
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	/**
	 * @desc category 전체 가져오기
	 * @return categories
	 */
	@Override
	public CategoryResponse selectCategories() {
		return new CategoryResponse(categoryDao.selectCategories());
	}

}
