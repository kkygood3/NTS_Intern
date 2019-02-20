package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.dto.product.Product;
import com.nts.reservation.dto.product.Promotion;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public interface ProductService {
	public static final Integer LIMIT = 4;

	List<Product> getProductsByCategory(Integer categoryId, Integer start);

	List<Promotion> getPromotions();

	Long getProductsCount();

	Long getProductsCountByCategory(Integer categoryId);

}
