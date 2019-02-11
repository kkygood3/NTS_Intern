/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.dto.MainProduct;

public interface MainProductService {
	public static final Integer LIMIT = 4;

	public List<MainProduct> getProducts(Integer start);

	public List<MainProduct> getProducts(Integer categoryId, Integer start);

	public int getCount();

	public int getCount(Integer categoryId);
}
