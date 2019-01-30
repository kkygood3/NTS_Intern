/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.CategoryDao;
import com.nts.dto.Category;
import com.nts.service.CategoryService;


/**
 *
 * @description : Category Service
 * @package : com.nts.service.impl
 * @filename : CategoryServiceImpl.java
 * @author : 최석현
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	@Transactional
	public List<Category> getItems() {
		List<Category> categoryList = categoryDao.selectGroup();
		List<Integer> countList = categoryDao.selectCounts();
		
		for(int i=0, len = categoryList.size(); i<len; i++) {
			int count = countList.get(i);
			categoryList.get(i).setCount(count);
		}
		
		return categoryList;
	}

}
