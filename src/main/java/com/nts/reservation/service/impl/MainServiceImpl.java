/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.MainCategoryDao;
import com.nts.reservation.dao.main.MainProductDao;
import com.nts.reservation.dao.main.MainPromotionDao;
import com.nts.reservation.dto.main.MainCategoryResponse;
import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.dto.main.MainProductResponse;
import com.nts.reservation.dto.main.MainPromotion;
import com.nts.reservation.dto.main.MainPromotionResponse;
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
	public MainProductResponse getProducts(int categoryId, int start, int pagingLimit) {
		int count = mainProductDao.selectCount(categoryId);
		List<MainProduct> productList = new ArrayList<>();
		
		if(count > 0) {
			 productList = mainProductDao.selectPagingProducts(categoryId, start, pagingLimit);
		}
		return new MainProductResponse(productList, count);
	}

	@Override
	public MainCategoryResponse getCategories(int pagingLimit) {
		return new MainCategoryResponse(mainCategoryDao.selectCategories(pagingLimit));
	}

	@Override
	public MainPromotionResponse getPromotions(int pagingLimit) {
		int count = mainPromotionDao.selectCount();
		List<MainPromotion> promotionList = new ArrayList<>();
		
		if(count > 0) {
			 promotionList = mainPromotionDao.selectPromotions(pagingLimit);
		}
		return new MainPromotionResponse(promotionList, count);
	}
}
