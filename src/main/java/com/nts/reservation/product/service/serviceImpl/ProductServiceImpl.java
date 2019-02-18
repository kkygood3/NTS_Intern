/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProductsByCategory(int categoryId, int start, int limit) {

		List<Product> products = productDao.selectProductsByCategory(categoryId, start, limit);
		if (products.size() == 0) {
			logger.warn("Does not exist categoryId / {} - displayInfoId : {}", this.getClass(), categoryId);
			throw new IllegalArgumentException("Bad Request! Parameter (categoryId)");
		}

		int productCount = productDao.selectProductCountByCategory(categoryId);
		return ProductResponse.builder()
			.items(products)
			.totalCount(productCount)
			.build();
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId, ImageType type) {
		return productDao.selectProductImages(displayInfoId, type);
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return productDao.selectProductPrices(displayInfoId);
	}
}
