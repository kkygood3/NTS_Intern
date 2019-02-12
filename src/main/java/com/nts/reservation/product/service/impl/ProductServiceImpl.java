/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.impl.ProductDaoImpl;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDaoImpl productDaoImpl;

	@Override
	public List<Product> getProducts(int categoryId, int start) {
		//오류 던지는 게 낫다
		if (start < 0) {
			//throw error
		}
		if (isAllCategories(categoryId)) {
			return getProducts(start);
		}
		return productDaoImpl.selectProductsByCategory(categoryId, start, PRODUCT_LIMIT);
	}

	@Override
	public List<Product> getProducts(int start) {
		//오류 던지는 게 낫다
		if (start < 0) {
			//throw error
		}
		return productDaoImpl.selectProducts(start, PRODUCT_LIMIT);
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		if (isAllCategories(categoryId)) {
			return getProductsCount();
		}
		return productDaoImpl.selectProductsCountByCategory(categoryId);
	}

	@Override
	public int getProductsCount() {
		return productDaoImpl.selectProductsCount();
	}

	private boolean isAllCategories(int categoryId) {
		if (categoryId == 0) {
			return true;
		}
		return false;
	}
}
