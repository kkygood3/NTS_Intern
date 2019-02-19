/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse getProductsByCategory(int categoryId, int start, int limit);
	List<ProductImage> getProductImages(int displayInfoId, ImageType type);
	List<ProductPrice> getProductPrices(int displayInfoId);
}
