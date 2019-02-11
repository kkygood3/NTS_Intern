/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.main.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.main.product.dao.ProductDao;
import com.nts.reservation.main.product.dto.Product;
import com.nts.reservation.main.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProducts(long categoryId, long start) {
		if (start < 0) {
			start = 0;
		}

		if (categoryId > 0) {
			return productDao.selectProductsByCategory(categoryId, start, PRODUCT_LIMIT);
		} else {
			return getProducts(start);
		}
	}

	@Override
	public List<Product> getProducts(long start) {
		if (start < 0) {
			start = 0;
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
