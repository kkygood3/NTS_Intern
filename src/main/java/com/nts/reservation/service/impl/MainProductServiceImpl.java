/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.MainProductDao;
import com.nts.reservation.dto.MainProduct;
import com.nts.reservation.service.MainProductService;

@Service
public class MainProductServiceImpl implements MainProductService {

	@Autowired
	MainProductDao mainProductDao;

	@Override
	@Transactional
	public List<MainProduct> getProducts(Integer start) {
		if (start < 0) {
			start = 0;
		}
		return mainProductDao.selectPagingProducts(start, MainProductService.LIMIT);
	}

	@Override
	@Transactional
	public List<MainProduct> getProducts(Integer categoryId, Integer start) {
		if (start < 0) {
			start = 0;
		}

		if (categoryId > 0) {
			return mainProductDao.selectPagingProductsByCategory(categoryId, start, MainProductService.LIMIT);
		} else {
			return getProducts(start);
		}
	}

	@Override
	public int getCount() {
		return mainProductDao.selectCount();
	}

	@Override
	public int getCount(Integer categoryId) {
		if (categoryId > 0) {
			return mainProductDao.selectCountByCategory(categoryId);
		} else {
			return getCount();
		}
	}

}
