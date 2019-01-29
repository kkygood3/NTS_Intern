/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.service;

import com.nts.exception.ProductParamException;
import com.nts.layer.product.dto.ProductResponse;

/**
 * @author 전연빈
 */
public interface ProductService {
	ProductResponse getProductsByCategory(int categoryId, int start) throws ProductParamException;
}
