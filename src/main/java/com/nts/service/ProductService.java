/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.ProductDto;

/**
 * 상품에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ProductService {
	public static final Integer LIMIT = 4;
	List<ProductDto> getProducts(int start);
	List<ProductDto> getProductsByCategoryId(int categoryId, int start);
	int getCount();
	int getCountByCategoryId(int categoryId);
}
