/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dao;

import java.util.List;

import com.nts.reservation.product.dto.Product;

public interface ProductDao {
	List<Product> selectPagingProducts(int start, int limit);

	List<Product> selectPagingProductsByCategory(int categoryId, int start, int limit);

	int selectCount();

	int selectCountByCategory(int categoryId);
}
