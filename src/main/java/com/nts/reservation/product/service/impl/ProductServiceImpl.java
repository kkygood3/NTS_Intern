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
	public ProductResponse getProducts(int start) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setItems(productDao.selectPagingProducts(start, ProductService.LIMIT));
		productResponse.setTotalCount(productDao.selectCount());

		return productResponse;
	}

	@Override
	public ProductResponse getProducts(int categoryId, int start) {
		ProductResponse productResponse = new ProductResponse();

		if (isTotalCategory(categoryId)) {
			productResponse = getProducts(start);
		} else {
			productResponse.setItems(productDao.selectPagingProductsByCategory(categoryId, start, ProductService.LIMIT));
			productResponse.setTotalCount(productDao.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		if (categoryId > 0) {
			return false;
		}

		return true;
	}

}
