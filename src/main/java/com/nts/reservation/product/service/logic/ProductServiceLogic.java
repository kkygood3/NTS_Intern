/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.common.annotation.IsEmpty;
import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.model.Product;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceLogic implements ProductService {

	private static final int ALL_CATEGORY = 0;

	@Autowired
	private ProductDao productDao;

	/** 
	 * 전체 혹은 특정 카테고리의 ProductList 반환
	 */
	@Override
	@IsEmpty
	public List<Product> getProductList(int categoryId, int start) {

		if (isAllCategory(categoryId)) {
			return productDao.selectAllCategoryProductList(start);
		} else {
			return productDao.selectOneCategoryProductList(categoryId, start);
		}
	}

	/** 
	 * 전체 혹은 특정 카테고리의 count 반환
	 */
	@Override
	public int getProductCount(int categoryId) {

		if (isAllCategory(categoryId)) {
			return productDao.selectAllCategoryProductCount();
		} else {
			return productDao.selectOneCategoryProductCount(categoryId);
		}
	}

	private boolean isAllCategory(int categoryId) {
		return categoryId == ALL_CATEGORY;
	}
}
