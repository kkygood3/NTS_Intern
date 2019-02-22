/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.MainCategoryDao;
import com.nts.reservation.dao.main.MainProductDao;
import com.nts.reservation.dao.main.MainPromotionDao;
import com.nts.reservation.dto.main.MainCategory;
import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.dto.main.MainPromotion;
import com.nts.reservation.service.MainService;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private MainProductDao mainProductDao;
	@Autowired
	private MainCategoryDao mainCategoryDao;
	@Autowired
	private MainPromotionDao mainPromotionDao;

	@Override
	public List<MainProduct> getProducts(int categoryId, int start, int pagingLimit) {
		//조회 시작점이 음수일때 맨 처음부터 조회
		if (start < 0) {
			start = 0;
		}

		//categoryId는 1부터 시작
		if (categoryId > 0) {
			return mainProductDao.selectPagingProductsByCategory(categoryId, start, pagingLimit);
		} else {
			//1보다 작은 값일때 카테고리 구분 없이 조회
			return getProducts(start, pagingLimit);
		}
	}

	@Override
	public List<MainProduct> getProducts(int start, int pagingLimit) {
		//조회 시작점이 음수일때 맨 처음부터 조회
		if (start < 0) {
			start = 0;
		}

		return mainProductDao.selectPagingProducts(start, pagingLimit);
	}

	@Override
	public int getProductCount(int categoryId) {
		//categoryId는 1부터 시작
		if (categoryId > 0) {
			return mainProductDao.selectCountByCategory(categoryId);
		} else {
			//1보다 작은 값일때 카테고리 구분 없이 조회
			return getProductCount();
		}
	}

	@Override
	public int getProductCount() {
		return mainProductDao.selectCount();
	}

	@Override
	public List<MainCategory> getCategories(int pagingLimit) {
		return mainCategoryDao.selectCategories(pagingLimit);
	}

	@Override
	public List<MainPromotion> getPromotions(int pagingLimit) {
		return mainPromotionDao.selectPromotions(pagingLimit);
	}

	@Override
	public int getPromotionCount() {
		return mainPromotionDao.selectCount();
	}
}
