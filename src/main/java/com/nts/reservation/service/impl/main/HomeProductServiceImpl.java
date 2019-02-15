/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.HomeProductDao;
import com.nts.reservation.dto.main.HomeProduct;
import com.nts.reservation.service.main.HomeProductService;

@Service
public class HomeProductServiceImpl implements HomeProductService {

	@Autowired
	private HomeProductDao homeProductDao;

	@Override
	public List<HomeProduct> getProducts(int categoryId, int start, int pagingLimit) {
		if (start < 0) {
			start = 0;
		}

		if (categoryId > 0) {
			return homeProductDao.selectPagingProductsByCategory(categoryId, start, pagingLimit);
		} else {
			return getProducts(start, pagingLimit);
		}
	}

	@Override
	public List<HomeProduct> getProducts(int start, int pagingLimit) {
		if (start < 0) {
			start = 0;
		}

		return homeProductDao.selectPagingProducts(start, pagingLimit);
	}

	@Override
	public int getCount(int categoryId) {
		if (categoryId > 0) {
			return homeProductDao.selectCountByCategory(categoryId);
		} else {
			return getCount();
		}
	}

	@Override
	public int getCount() {
		return homeProductDao.selectCount();
	}
}
