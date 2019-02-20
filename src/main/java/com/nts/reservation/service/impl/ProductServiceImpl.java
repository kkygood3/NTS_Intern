package com.nts.reservation.service.impl;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.product.Product;
import com.nts.reservation.dto.product.Promotion;
import com.nts.reservation.service.ProductService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProductsByCategory(Integer categoryId, Integer start) {
		if (categoryId > 0) {
			return productDao.selectProductsByCategory(categoryId, start, LIMIT);
		} else {
			return productDao.selectProducts(start, LIMIT);
		}
	}

	@Override
	public Long getProductsCount() {
		return productDao.selectProductsCount();
	}

	@Override
	public Long getProductsCountByCategory(Integer categoryId) {
		return productDao.selectProductsCountByCategory(categoryId);
	}

	@Override
	public List<Promotion> getPromotions() {
		return productDao.selectPromotions();
	}
}
