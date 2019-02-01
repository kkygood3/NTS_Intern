/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import com.nts.project3.dto.MainCategory;

public interface MainCategoryService {
	/**
	 * @value	10
	 */
	public static final Integer LIMIT = 10;
	
	/**
	 * category_api_view를 조회
	 * @return default LIMIT만큼 조회하여 반환
	 */
	public List<MainCategory> getCategories();
}
