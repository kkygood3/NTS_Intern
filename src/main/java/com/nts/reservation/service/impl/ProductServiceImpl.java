/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public int getCountAllProducts() {
		return productDao.selectAll();
	}

	@Override
	public int getCount(int categoryId) {
		return productDao.selectByCategory(categoryId);
	}

	@Override
	public List<Product> getProducts(int categoryId, int start) {
		return productDao.getProducts(categoryId, start);
	}

	@Override
	public List<Product> getPromotions() {
		return productDao.getPromotions();
	}

	@Override
	public int getCountPromotions() {
		return productDao.getCountPromotions();
	}
}
