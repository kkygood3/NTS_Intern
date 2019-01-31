/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.MainPageCategory;

public interface MainPageCategoryService extends DefaultService {
	/**
	 * category_api_view를 조회
	 * @return default LIMIT만큼 조회하여 반환
	 */
	public List<MainPageCategory> getCategories();
}
