/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.category.impl;

import org.springframework.stereotype.Service;

import com.nts.dao.category.CategoryRepository;
import com.nts.dto.category.Categories;
import com.nts.service.category.CategoryService;

/**
 * @author 전연빈
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * @desc category 전체 가져오기
	 * @return categories
	 */
	@Override
	public Categories getCategories() {
		return new Categories(categoryRepository.selectCategories());
	}

}
