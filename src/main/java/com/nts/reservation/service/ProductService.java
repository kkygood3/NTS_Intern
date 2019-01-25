/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.model.ProductResponse;

public interface ProductService {

	public ProductResponse getProductResponse(int categoryId, int start);

}
