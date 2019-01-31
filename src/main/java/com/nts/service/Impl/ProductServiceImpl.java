/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.ProductDao;
import com.nts.dto.Product;
import com.nts.service.ProductService;

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
