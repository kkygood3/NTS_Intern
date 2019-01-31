/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.MainPageProduct;

public interface MainPageProductService extends DefaultService{
	/**
	 * product_api_view에 Select 수행시 한번에 가져올 row 수
	 * @value	4 
	 */
	public static final Integer LIMIT = 4;
	
	public List<MainPageProduct> getProductsByCategory(long categoryId, long start);
	public List<MainPageProduct> getProducts(long start);
	
	public int getCountByCategory(int categoryId);
	public int getCount();
}
