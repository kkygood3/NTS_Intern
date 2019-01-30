package com.nts.reservation.service.impl;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProductsByCategory(Integer start, Integer category) {
		if (category > 0) {
			return productDao.getProductsByCategory(start, category, LIMIT);
		} else {
			return productDao.getProducts(start, LIMIT);
		}
	}
	
	@Override
	public Long getProductsCount() {
		return productDao.getProductsCount();
	}

	@Override
	public List<Promotion> getPromotions() {
		return productDao.getPromotions();
	}

	@Override
	public List<Category> getProductsCountByCategory() {
		return productDao.getProductsCountByCategory();
	}

}
