/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.CategoryDao;
import com.nts.project3.dto.CategoryDto;

/**
 * 카테고리 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryDto> getCategoriesWithProductCount() {
		return categoryDao.selectLimitedListWithProductCount(SELECT_LIMIT);
	}
}
