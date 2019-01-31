/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProductInfo;
import com.nts.reservation.model.ProductRequirer;

public interface ProductService {

	public List<Product> getProductList(ProductRequirer productRequirer);

	int getProductCount(ProductRequirer productRequirer);

	ProductInfo getProductInfo(ProductRequirer productRequirer);

}
