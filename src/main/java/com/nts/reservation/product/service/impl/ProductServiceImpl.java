/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.impl.ProductDaoImpl;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDaoImpl productDaoImpl;

	@Override
	public List<Product> getProductsByCategoryId(int categoryId, int start, int limit) {
		if (isAllCategories(categoryId)) {
			return getProducts(start, limit);
		}
		return productDaoImpl.selectProductsByCategoryId(categoryId, start, limit);
	}

	@Override
	public List<Product> getProducts(int start, int limit) {
		return productDaoImpl.selectProducts(start, limit);
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		if (isAllCategories(categoryId)) {
			return getProductsCount();
		}
		return productDaoImpl.selectProductsCountByCategoryId(categoryId);
	}

	@Override
	public int getProductsCount() {
		return productDaoImpl.selectProductsCount();
	}

	private boolean isAllCategories(int categoryId) {
		if (categoryId == 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProductImage> getProductImage(int displayInfoId) {
		return productDaoImpl.selectProductImage(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrice(int displayInfoId) {
		return productDaoImpl.selectProductPrice(displayInfoId);
	}

	@Override
	public ProductExtraImage getProductExtraImage(int displayInfoId) {
		return productDaoImpl.selectProductExtraImage(displayInfoId);
	}
}
