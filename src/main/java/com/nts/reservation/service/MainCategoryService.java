/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.MainCategory;

public interface MainCategoryService {
	/**
	 * @value	10
	 */
	Integer LIMIT = 10;
	
	/**
	 * category_api_view를 조회
	 * @return default LIMIT만큼 조회하여 반환
	 */
	List<MainCategory> getCategories();
}
