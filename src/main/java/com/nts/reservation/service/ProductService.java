/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.model.Product;
import com.nts.reservation.model.ProducsListInfo;
import com.nts.reservation.model.ProductRequirer;

public interface ProductService {

	List<Product> getProductList(int categoryId, int start);

	int getProductCount(int categoryId);

	ProducsListInfo getProductInfo(ProductRequirer productRequirer);

}
