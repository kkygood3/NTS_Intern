/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.service;

import com.nts.layer.product.dto.ProductResponse;

/**
 * @author 전연빈
 */
public interface ProductService {
	public ProductResponse getProductsByCategory(String categoryId, int start);
}
