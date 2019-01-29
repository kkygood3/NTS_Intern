/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.ProductApiViewDao;
import com.nts.dto.ProductApiView;
import com.nts.service.ProductApiViewService;

@Service
public class ProductApiViewServiceImpl implements ProductApiViewService {

	@Autowired
	ProductApiViewDao productApiViewDao;

	@Override
	public List<ProductApiView> getProducts(long categoryId, long start) {
		if (categoryId > 0)
			return productApiViewDao.selectPagingProducts(categoryId, start, LIMIT);
		else
			return getProducts(start);
	}

	@Override
	public List<ProductApiView> getProducts(long start) {
		return productApiViewDao.selectAllPagingProducts(start, LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		if (categoryId > 0)
			return productApiViewDao.selectCount(categoryId);
		else
			return getCount();
	}

	@Override
	public int getCount() {
		return productApiViewDao.selectCountAll();
	}
}
