package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.product.Category;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
public interface CategoryService {
	List<Category> getAllProductsCountByCategory();
}
