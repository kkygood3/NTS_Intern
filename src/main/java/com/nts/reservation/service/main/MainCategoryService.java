/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.main;

import java.util.List;

import com.nts.reservation.dto.main.MainCategory;

public interface MainCategoryService {
	/**
	 * @value	10
	 */
	Integer LIMIT = 10;

	/**
	 * api/categories response에 사용
	 * @return MainCategory List
	 */
	List<MainCategory> getCategories();
}
