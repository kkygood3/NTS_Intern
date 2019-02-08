/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.category.service;

import java.util.List;

import com.nts.reservation.main.category.dto.MainCategory;

public interface MainCategoryService {
	public List<MainCategory> getCategories();
}
