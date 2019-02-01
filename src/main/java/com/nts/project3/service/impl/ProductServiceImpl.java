/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.ProductDao;
import com.nts.project3.dto.Product;
import com.nts.project3.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProducts(Integer start) {
		return productDao.selectProductsPage(start, LIMIT);
	}

	@Override
	public Product getProductById(Product product) {
		return productDao.selectProductsById(product.getId(), LIMIT);
	}

	@Override
	public int getCount() {
		return productDao.selectCount();
	}

}
