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
		//조회 시작점이 음수일때 맨 처음부터 조회
		if (start < 0) {
			start = 0;
		}

		//categoryId는 1부터 시작
		if (categoryId > 0) {
			return homeProductDao.selectPagingProductsByCategory(categoryId, start, pagingLimit);
		} else {
			//1보다 작은 값일때 카테고리 구분 없이 조회
			return getProducts(start, pagingLimit);
		}
	}

	@Override
	public List<HomeProduct> getProducts(int start, int pagingLimit) {
		//조회 시작점이 음수일때 맨 처음부터 조회
		if (start < 0) {
			start = 0;
		}

		return homeProductDao.selectPagingProducts(start, pagingLimit);
	}

	@Override
	public int getCount(int categoryId) {
		//categoryId는 1부터 시작
		if (categoryId > 0) {
			return homeProductDao.selectCountByCategory(categoryId);
		} else {
			//1보다 작은 값일때 카테고리 구분 없이 조회
			return getCount();
		}
	}

	@Override
	public int getCount() {
		return homeProductDao.selectCount();
	}
}
