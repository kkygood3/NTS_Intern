/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

public interface ProductService {
	public static final int LIMIT = 4;
	
	public List<Product> getProductsByCategory(int categoryId, int start);
	public int getProductsCountByCategory(int categoryId);
	
}
