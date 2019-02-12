/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.MainProductDao;
import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.service.main.MainProductService;

@Service
public class MainProductServiceImpl implements MainProductService {

	@Autowired
	private MainProductDao mainProductDao;

	@Override
	public List<MainProduct> getProducts(long categoryId, long start) {
		if (start < 0) {
			start = 0;
		}

		if (categoryId > 0) {
			return mainProductDao.selectPagingProductsByCategory(categoryId, start, LIMIT);
		} else {
			return getProducts(start);
		}
	}

	@Override
	public List<MainProduct> getProducts(long start) {
		if (start < 0) {
			start = 0;
		}

		return mainProductDao.selectPagingProducts(start, LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		if (categoryId > 0) {
			return mainProductDao.selectCountByCategory(categoryId);
		} else {
			return getCount();
		}
	}

	@Override
	public int getCount() {
		return mainProductDao.selectCount();
	}
}
