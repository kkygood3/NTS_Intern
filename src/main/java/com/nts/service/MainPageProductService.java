/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.MainPageProduct;

public interface MainPageProductService {
	public static final Integer LIMIT = 4;
	
	public List<MainPageProduct> getProducts(long categoryId, long start);
	public List<MainPageProduct> getProducts(long start);
	
	public int getCount(int categoryId);
	public int getCount();
}
