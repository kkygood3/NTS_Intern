/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CategoryService {
	public static final int CATEGORY_LIMIT = 10;

	public List<Category> getCategoryList();
}
