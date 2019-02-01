package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.products.dto.Category;
import com.nts.reservation.products.dto.Product;
import com.nts.reservation.products.dto.Promotion;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public interface ProductService {
	public static final Integer LIMIT = 4;

	public abstract List<Product> getProductsByCategory(Integer categoryId, Integer start);

	public abstract List<Promotion> getPromotions();

	public abstract Long getProductsCount();

	public abstract Long getProductsCountByCategory(Integer categoryId);

	public abstract List<Category> getAllProductsCountByCategory();

}
