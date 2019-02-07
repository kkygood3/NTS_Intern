/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.product;

import java.util.List;

import com.nts.dto.product.Product;
import com.nts.dto.product.Products;
import com.nts.exception.ProductParamException;

/**
 * @author 전연빈
 */
public interface ProductService {
	Products getProducts(int categoryId, int start) throws ProductParamException;

	List<Product> getProductsAll(int start) throws ProductParamException;

	int getProductsAllCount();

	List<Product> getProductsByCategory(int categoryId, int start) throws ProductParamException;

	int getProductsCountByCategory(int categoryId);
}
