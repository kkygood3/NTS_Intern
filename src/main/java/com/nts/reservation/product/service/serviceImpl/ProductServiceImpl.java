/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public ProductResponse getProductsByCategory(int categoryId, int start) {
		return ProductResponse.builder()
			.items(productDao.selectProductsByCategory(categoryId, start, LIMIT))
			.totalCount(productDao.selectProductCountByCategory(categoryId))
			.build();
	}
}
