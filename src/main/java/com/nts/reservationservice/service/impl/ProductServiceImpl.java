/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.ProductDao;
import com.nts.reservationservice.dto.ProductDto;
import com.nts.reservationservice.service.ProductService;

/*
 * @author 육성렬
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public List<ProductDto> getProducts(int start) {
		List<ProductDto> result = productDao.selectProduct(start, ProductService.LIMIT);
		return result;
	}

	@Override
	@Transactional
	public List<ProductDto> getProducts(int start, int categoryId) {
		List<ProductDto> result = productDao.selectProductByCategory(start, ProductService.LIMIT, categoryId);
		return result;
	}

	@Override
	@Transactional
	public int getCount() {
		return productDao.countAll();
	}

	@Override
	@Transactional
	public int getCount(int categoryId) {
		return productDao.countByCategory(categoryId);
	}

	@Override
	@Transactional
	public List<ProductDto> getPromotionProducts() {
		return productDao.selectPromotionProduct();
	}
}
