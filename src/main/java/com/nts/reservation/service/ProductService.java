package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

import java.util.Map;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<Product> getProductsByCategory(Integer start, Integer category);

	public List<Promotion> getPromotions();

	public List<Category> getProductsCountByCategory();

	public Long getProductsCount();
}
