/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse getProducts(int start, int count);

	ProductResponse getProducts(int categoryId, int start, int count);

	List<ProductImage> getProductImages(int displayInfoId);

	List<ProductPrice> getProductPrices(int displayInfoId);

}
