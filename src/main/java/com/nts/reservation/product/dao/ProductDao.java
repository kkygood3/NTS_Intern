/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dao;

import java.util.List;

import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

public interface ProductDao {
	List<Product> selectPagingProducts(int start, int limit);

	List<Product> selectPagingProductsByCategory(int categoryId, int start, int limit);

	List<ProductImage> selectProductImagesByDisplayInfoId(int displayInfoId);

	List<ProductPrice> selectProductPricesByDisplayInfoId(int displayInfoId);

	int selectCount();

	int selectCountByCategory(int categoryId);
}
