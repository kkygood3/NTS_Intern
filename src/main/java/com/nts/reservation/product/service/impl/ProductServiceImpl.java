/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProducts(long categoryId, long start) {
		//오류 던지는 게 낫다
		if (start < 0) {
			//throw error
		}

		if (categoryId > 0) {
			return productDao.selectProductsByCategory(categoryId, start, PRODUCT_LIMIT);
		}
		return getProducts(start);
	}

	@Override
	public List<Product> getProducts(long start) {
		//오류 던지는 게 낫다
		if (start < 0) {
			//throw error
		}

		return productDao.selectProducts(start, PRODUCT_LIMIT);
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		if (categoryId > 0) {
			return productDao.selectProductsCountByCategory(categoryId);
		} else {
			return getProductsCount();
		}
	}

	@Override
	public int getProductsCount() {
		return productDao.selectProductsCount();
	}
}
