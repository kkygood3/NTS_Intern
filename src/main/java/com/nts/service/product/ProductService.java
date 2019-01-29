/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.product;

import com.nts.dto.product.ProductResponse;
import com.nts.exception.ProductParamException;

/**
 * @author 전연빈
 */
public interface ProductService {
	ProductResponse getProductsByCategory(int categoryId, int start) throws ProductParamException;
}
