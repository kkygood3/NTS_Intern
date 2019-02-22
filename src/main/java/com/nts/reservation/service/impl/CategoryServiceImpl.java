/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.mapper.CategoryMapper;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDto> getCategoriesWithProductsOnDisplayCount(PageDto page) {
		return categoryMapper.selectCategories(page);
	}
}
