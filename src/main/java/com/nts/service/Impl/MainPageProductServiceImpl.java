/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.MainPageProductDao;
import com.nts.dto.MainPageProduct;
import com.nts.service.MainPageProductService;

@Service
public class MainPageProductServiceImpl implements MainPageProductService {

	@Autowired
	private MainPageProductDao mainPageProductDao;

	@Override
	public List<MainPageProduct> getProductsByCategory(long categoryId, long start) {
		return mainPageProductDao.selectPagingProductsByCategory(categoryId, start, LIMIT);
	}

	@Override
	public List<MainPageProduct> getProducts(long start) {
		return mainPageProductDao.selectPagingProducts(start, LIMIT);
	}

	@Override
	public int getCountByCategory(int categoryId) {
		return mainPageProductDao.selectCountByCategory(categoryId);
	}

	@Override
	public int getCount() {
		return mainPageProductDao.selectCount();
	}
}
