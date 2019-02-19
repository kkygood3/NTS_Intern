/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDaoImpl;

	private static final int All_CATEGORIES = 0;

	@Override
	public List<Product> getProductsByCategoryId(int categoryId, int start, int limit) {
		ArgumentValidator.isCorrectCategoryId(categoryId);
		ArgumentValidator.isCorrectStartAndLimit(start, limit);

		if (categoryId == All_CATEGORIES) {
			return getProducts(start, limit);
		}
		return productDaoImpl.selectProductsByCategoryId(categoryId, start, limit);
	}

	@Override
	public List<Product> getProducts(int start, int limit) {
		ArgumentValidator.isCorrectStartAndLimit(start, limit);

		return productDaoImpl.selectProducts(start, limit);
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		ArgumentValidator.isCorrectCategoryId(categoryId);

		if (categoryId == All_CATEGORIES) {
			return getProductsCount();
		}
		return productDaoImpl.selectProductsCountByCategoryId(categoryId);
	}

	@Override
	public int getProductsCount() {
		return productDaoImpl.selectProductsCount();
	}

	@Override
	public ProductResponse getProductResponse(int categoryId, int start, int limit, int totalCount) {
		ArgumentValidator.isCorrectCategoryId(categoryId);
		ArgumentValidator.isCorrectStartAndLimit(start, limit);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(totalCount);
		productResponse.setItems(getProductsByCategoryId(categoryId, start, limit));

		return productResponse;
	}

	@Override
	public List<ProductImage> getProductImage(int displayInfoId) {
		ArgumentValidator.isCorrectDisplayInfoId(displayInfoId);

		return productDaoImpl.selectProductImage(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrice(int displayInfoId) {
		ArgumentValidator.isCorrectDisplayInfoId(displayInfoId);

		return productDaoImpl.selectProductPrice(displayInfoId);
	}

	@Override
	public ProductExtraImage getProductExtraImage(int displayInfoId) {
		ArgumentValidator.isCorrectDisplayInfoId(displayInfoId);

		ProductExtraImage productExtraImage = productDaoImpl.selectProductExtraImage(displayInfoId);

		if (productExtraImage == null) {
			productExtraImage = new ProductExtraImage();
			productExtraImage.setProductImage("none");
		}

		return productExtraImage;
	}
}
