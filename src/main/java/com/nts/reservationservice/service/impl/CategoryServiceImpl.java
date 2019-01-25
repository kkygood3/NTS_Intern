/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.CategoryDao;
import com.nts.reservationservice.dto.CategoryDto;
import com.nts.reservationservice.service.CategoryService;

/*
 * @author 육성렬
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	@Transactional
	public List<CategoryDto> getCategorys() {
		List<CategoryDto> list = categoryDao.selectCategory();
		return list;
	}

}
