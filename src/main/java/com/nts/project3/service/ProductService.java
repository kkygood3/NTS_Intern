/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import com.nts.project3.dto.ProductResponseDto;

/**
 * 상품에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ProductService {
	public static final int SELECT_LIMIT = 4;
	ProductResponseDto getProductResponse(int start);
	ProductResponseDto getProductResponse(int categoryId, int start);
}
