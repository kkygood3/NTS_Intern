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
	private MainPageProductDao productApiViewDao;

	@Override
	public List<MainPageProduct> getProducts(long categoryId, long start) {
		if (categoryId > 0) 
			return productApiViewDao.selectPagingProductsByCategory(categoryId, start, LIMIT);
		else
			return getProducts(start);
	}

	@Override
	public List<MainPageProduct> getProducts(long start) {
		return productApiViewDao.selectPagingProducts(start, LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		if (categoryId > 0)
			return productApiViewDao.selectCountByCategory(categoryId);
		else
			return getCount();
	}

	@Override
	public int getCount() {
		return productApiViewDao.selectCount();
	}
}
