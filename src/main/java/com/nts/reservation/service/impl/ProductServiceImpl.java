/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.service.ProductService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductDto> getProductList(int start) {
		List<ProductDto> result = productDao.selectProduct(start, ProductService.LIMIT);
		return result;
	}

	@Override
	public List<ProductDto> getProductList(int start, int categoryId) {
		List<ProductDto> result = productDao.selectProductByCategory(start, ProductService.LIMIT, categoryId);
		return result;
	}

	@Override
	public int getCount() {
		return productDao.countAll();
	}

	@Override
	public int getCount(int categoryId) {
		return productDao.countByCategory(categoryId);
	}

	@Override
	public List<ProductDto> getPromotionProductList() {
		return productDao.selectPromotionProduct();
	}
}
