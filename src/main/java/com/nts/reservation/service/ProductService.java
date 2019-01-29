/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.model.ProductRequest;
import com.nts.reservation.model.ProductResponse;

public interface ProductService {

	ProductResponse getProductResponse(ProductRequest productRequest);

}
