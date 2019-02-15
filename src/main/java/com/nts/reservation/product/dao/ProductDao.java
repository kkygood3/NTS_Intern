/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao;

import java.util.List;

import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

public interface ProductDao {
	List<Product> selectProductsByCategory(int categoryId, int start, int limit);

	int selectProductCountByCategory(int categoryId);

	List<ProductImage> selectProductImages(int displayInfoId, ImageType type);

	List<ProductPrice> selectProductPrices(int displayInfoId);
}
