/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ProductService {
	public static final int PRODUCT_LIMIT = 4;

	List<Product> getProducts(long categoryId, long start);

	List<Product> getProducts(long start);

	int getCount(int categoryId);

	int getCount();
}
