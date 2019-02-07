/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.model.Category;
import com.nts.reservation.service.CategoryService;

@Service
public class CategoryServiceLogic implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	/**
	 * dao에서 category List 객체를 받아 CategoryResponse 객체로 생성 후 반환
	 */
	@Override
	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

}
