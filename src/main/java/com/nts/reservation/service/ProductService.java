/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ProductDto;

/**
 * @author 육성렬
 */
public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<ProductDto> getProductList(int start);

	public List<ProductDto> getProductList(int start, int categoryId);

	public List<ProductDto> getPromotionProductList();

	public int getCount();

	public int getCount(int categoryId);
}
