/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDaoImpl;

	@Override
	public ProductResponse getProducts(int start, int count) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setItems(productDaoImpl.selectPagingProducts(start, count));
		productResponse.setTotalCount(productDaoImpl.selectCount());

		return productResponse;
	}

	@Override
	public ProductResponse getProducts(int categoryId, int start, int count) {
		ProductResponse productResponse = new ProductResponse();

		if (isTotalCategory(categoryId)) {
			productResponse = getProducts(start, count);
		} else {
			productResponse.setItems(productDaoImpl.selectPagingProductsByCategoryId(categoryId, start, count));
			productResponse.setTotalCount(productDaoImpl.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		return (categoryId == 0);
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return productDaoImpl.selectProductImages(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return productDaoImpl.selectProductPrices(displayInfoId);
	}

}
