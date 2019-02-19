/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.model.Product;
import com.nts.reservation.product.model.ProductListInfo;
import com.nts.reservation.product.model.ProductRequirer;

public interface ProductService {

	List<Product> getProductList(int categoryId, int start);

	int getProductCount(int categoryId);

	ProductListInfo getProductListInfo(ProductRequirer productRequirer);

}
