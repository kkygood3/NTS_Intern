/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.MainProductDao;
import com.nts.reservation.product.dto.MainProduct;
import com.nts.reservation.product.service.MainProductService;

@Service
public class MainProductServiceImpl implements MainProductService {

	@Autowired
	private MainProductDao mainProductDao;

	@Override
	public List<MainProduct> getProducts(Integer start) {
		return mainProductDao.selectPagingProducts(start, MainProductService.LIMIT);
	}

	@Override
	public List<MainProduct> getProducts(Integer categoryId, Integer start) {
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
