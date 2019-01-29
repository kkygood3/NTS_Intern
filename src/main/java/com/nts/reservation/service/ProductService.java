package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

import java.util.Map;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public Map<String, Object> getProductsByCategory(Integer start, Integer category);

	public Map<String, Object> getPromotions();

	public Map<String, Object> getProductsCountByCategory();
}
