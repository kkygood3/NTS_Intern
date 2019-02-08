/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.ProductResponse;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public ProductResponse getProductsByCategory(int categoryId, int start) {
		return ProductResponse.builder()
			.items(productDao.getProductsByCategory(categoryId, start, LIMIT))
			.totalCount(productDao.getProductCountByCategory(categoryId))
			.build();
	}
}
