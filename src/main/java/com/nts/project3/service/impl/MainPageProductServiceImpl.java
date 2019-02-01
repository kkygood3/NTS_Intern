/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.MainPageProductDao;
import com.nts.project3.dto.MainPageProduct;
import com.nts.project3.service.MainPageProductService;

@Service
public class MainPageProductServiceImpl implements MainPageProductService {

	@Autowired
	private MainPageProductDao mainPageProductDao;

	@Override
	public List<MainPageProduct> getProducts(long categoryId, long start) {
		if(categoryId > 0)
			return mainPageProductDao.selectPagingProductsByCategory(categoryId, start, LIMIT);
		else
			return getProducts(start);
	}

	@Override
	public List<MainPageProduct> getProducts(long start) {
		return mainPageProductDao.selectPagingProducts(start, LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		if(categoryId > 0)
			return mainPageProductDao.selectCountByCategory(categoryId);
		else
			return getCount();
	}

	@Override
	public int getCount() {
		return mainPageProductDao.selectCount();
	}
}
