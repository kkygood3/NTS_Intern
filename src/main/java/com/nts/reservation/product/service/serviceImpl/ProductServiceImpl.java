/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProductsByCategory(int categoryId, int start, int limit) {
		List<Product> products = productDao.selectProductsByCategory(categoryId, start, limit);
		if (products.size() == 0) {
			return ProductResponse.builder()
				.items(Collections.emptyList())
				.totalCount(0)
				.build();
		}

		int productCount = productDao.selectProductCountByCategory(categoryId);
		return ProductResponse.builder()
			.items(products)
			.totalCount(productCount)
			.build();
	}
}
