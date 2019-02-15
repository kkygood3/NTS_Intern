/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service.impl;

import java.util.ArrayList;
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
			productResponse.setItems(productDaoImpl.selectPagingProductsByCategory(categoryId, start, count));
			productResponse.setTotalCount(productDaoImpl.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		if (categoryId == 0) {
			return true;
		}

		return false;
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		List<ProductImage> comment = new ArrayList<ProductImage>();

		comment = productDaoImpl.selectProductImagesByDisplayInfoId(displayInfoId);

		return comment;
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		List<ProductPrice> comment = new ArrayList<ProductPrice>();

		comment = productDaoImpl.selectProductPricesByDisplayInfoId(displayInfoId);

		return comment;
	}

}
