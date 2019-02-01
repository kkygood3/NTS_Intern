/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProductByCategory(int categoryId, int start) {
		return productDao.selectAllProductGroupByCategory(categoryId, start, LIMIT);
	}

	@Override
	public int getNumberOfProducts(int categoryId) {
		return productDao.getNumberOfProducts(categoryId);
	}

}
