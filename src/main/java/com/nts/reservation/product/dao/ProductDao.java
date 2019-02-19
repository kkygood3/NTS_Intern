/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao;

import java.util.List;

import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface ProductDao {
	List<Product> selectProducts(int start, int limit);

	List<Product> selectProductsByCategoryId(int categoryId, int start, int limit);

	int selectProductsCount();

	int selectProductsCountByCategoryId(int categoryId);

	List<ProductImage> selectProductImage(int displayInfoId);

	List<ProductPrice> selectProductPrice(int displayInfoId);

	ProductExtraImage selectProductExtraImage(int displayInfoId);
}
