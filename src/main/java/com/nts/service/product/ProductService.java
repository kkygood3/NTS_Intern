/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.product;

import java.security.InvalidParameterException;

import com.nts.dto.product.Products;

/**
 * @author 전연빈
 */
public interface ProductService {
	Products getProducts(int categoryId, int start) throws InvalidParameterException;
}
