/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.CategoryDao;
import com.nts.dto.CategoryDto;

/**
 * 카테고리에 대한 서비스로직
 * @author jinwoo.bae
 */
@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;

	public List<CategoryDto> getCategories() {
		return categoryDao.selectAll();
	}
}
