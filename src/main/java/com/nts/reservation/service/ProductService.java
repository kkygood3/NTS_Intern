/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

public interface ProductService {
	public int getCountAllProducts();

	public int getCount(int categoryId);

	public List<Product> getProducts(int categoryId, int start);

	public List<Product> getPromotions();

	public int getCountPromotions();
}
