/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.Product;

public interface ProductService extends DefaultService {
	/**
	 * product에 Select 수행시 Paging 처리를 위해 한번에 가져올 row 수
	 * @value	4 
	 */
	public static final Integer LIMIT = 4;

	public List<Product> getProducts(Integer start);

	public Product getProductById(Product product);

	public int getCount();
}
