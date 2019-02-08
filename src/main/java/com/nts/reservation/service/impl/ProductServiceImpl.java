/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProducts(long categoryId, long start) {
		return productDao.selectProductsByCategory(categoryId, start, PRODUCT_LIMIT);
	}

	@Override
	public List<Product> getProducts(long start) {
		return productDao.selectProducts(start, PRODUCT_LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		return productDao.selectProductsCountByCategory(categoryId);
	}

	@Override
	public int getCount() {
		return productDao.selectProductsCount();
	}
}
