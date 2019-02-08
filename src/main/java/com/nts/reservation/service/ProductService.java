/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.ProductResponse;

public interface ProductService {
	public static final int LIMIT = 4;
	
	public ProductResponse getProductsByCategory(int categoryId, int start);
	
}
