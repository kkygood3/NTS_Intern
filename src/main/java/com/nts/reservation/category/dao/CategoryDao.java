/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.dao;

import java.util.List;

import com.nts.reservation.category.dto.Category;

public interface CategoryDao {

	List<Category> selectPagingCategories();
}
