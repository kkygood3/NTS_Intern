/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.dto.Product;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ProductService {
	List<Product> getProductsByCategoryId(int categoryId, int start, int limit);

	List<Product> getProducts(int start, int limit);

	int getProductsCountByCategoryId(int categoryId);

	int getProductsCount();
}
