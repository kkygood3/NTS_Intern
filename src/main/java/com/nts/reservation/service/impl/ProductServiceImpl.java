/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

/**
* @author  : 이승수
*/
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private PromotionDao promotionDao;

	@Override
	public int getCountAllProducts() {
		return productDao.selectCountAllProducts();
	}

	@Override
	public int getCount(int categoryId) {
		return productDao.selectByCategory(categoryId);
	}

	@Override
	public List<Product> getProducts(Integer categoryId, int start) {
		return productDao.selectProducts(categoryId, start, ProductService.LIMIT);
	}

	@Override
	public List<Product> getPromotions() {
		return promotionDao.selectPromotions();
	}

	@Override
	public int getCountPromotions() {
		return promotionDao.selectCountPromotions();
	}
}