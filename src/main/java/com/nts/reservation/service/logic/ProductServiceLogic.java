/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductInfo;
import com.nts.reservation.model.ProductRequirer;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceLogic implements ProductService {

	@Autowired
	private ProductDao productDao;

	public List<Product> getProductList(ProductRequirer productRequirer) {

		if (productRequirer.isAllCategory()) {
			return productDao.getAllCategoryProductList(productRequirer.getStart());
		} else {
			return productDao.getOneCategoryProductList(productRequirer.getCategoryId(), productRequirer.getStart());
		}
	}

	public int getProductCount(ProductRequirer productRequirer) {

		if (productRequirer.isAllCategory()) {
			return productDao.getAllCategoryProductCount();
		} else {
			return productDao.getOneCategoryProductCount(productRequirer.getCategoryId());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ProductInfo getProductInfo(ProductRequirer productRequirer) {

		List<Product> productList = this.getProductList(productRequirer);
		int productCount = this.getProductCount(productRequirer);

		return new ProductInfo(productList, productCount);
	}
}
