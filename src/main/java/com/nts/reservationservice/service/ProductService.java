/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service;

import java.util.List;

import com.nts.reservationservice.dto.ProductDto;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<ProductDto> getProducts(int start);

	public List<ProductDto> getProducts(int start, int categoryId);

	public int getCount();

	public int getCount(int categoryId);
}
