/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductListInfo;
import com.nts.reservation.model.ProductRequirer;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceLogic implements ProductService {

	private static final int ALL_CATEGORY = 0;

	@Autowired
	private ProductDao productDao;

	/** 
	 * 전체 혹은 특정 카테고리의 ProductList 반환
	 */
	public List<Product> getProductList(int categoryId, int start) {

		if (isAllCategory(categoryId)) {
			return productDao.getAllCategoryProductList(start);
		} else {
			return productDao.getOneCategoryProductList(categoryId, start);
		}
	}

	/** 
	 * 전체 혹은 특정 카테고리의 count 반환
	 */
	public int getProductCount(int categoryId) {

		if (isAllCategory(categoryId)) {
			return productDao.getAllCategoryProductCount();
		} else {
			return productDao.getOneCategoryProductCount(categoryId);
		}
	}

	/** 
	 * 전체 혹은 특정 카테고리의 productList, count를 가진 객체 반환
	 */
	@Override
	@Transactional(readOnly = true)
	public ProductListInfo getProductListInfo(ProductRequirer productRequirer) {

		List<Product> productList = getProductList(productRequirer.getCategoryId(), productRequirer.getStart());
		int productCount = getProductCount(productRequirer.getCategoryId());

		return new ProductListInfo(productList, productCount);
	}

	private boolean isAllCategory(int categoryId) {
		return categoryId == ALL_CATEGORY;
	}
}
