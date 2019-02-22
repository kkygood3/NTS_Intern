/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

/**
* @author  : 이승수
*/
public interface ProductService {
	public static final Integer LIMIT = 4;

	public int getCount(Integer categoryId);

	public List<Product> getProducts(Integer categoryId, int start);

	public List<Product> getPromotions();

	public int getCountPromotions();
}
