/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProducts(int start, int count) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setItems(productDao.selectPagingProducts(start, count));
		productResponse.setTotalCount(productDao.selectCount());

		return productResponse;
	}

	@Override
	public ProductResponse getProducts(int categoryId, int start, int count) {
		ProductResponse productResponse = new ProductResponse();

		if (isTotalCategory(categoryId)) {
			productResponse = getProducts(start, count);
		} else {
			productResponse.setItems(productDao.selectPagingProductsByCategory(categoryId, start, count));
			productResponse.setTotalCount(productDao.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		if (categoryId == 0) {
			return true;
		}

		return false;
	}

}
