/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.resevation.dao.CategoryDao;
import com.nts.resevation.dto.CategoryDto;

/**
 * 카테고리 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryDto> getCategoriesWithProductsOnDisplayCount(int limit) {
		return categoryDao.selectCategoriesWithProductsOnDisplayCount(limit);
	}
}
