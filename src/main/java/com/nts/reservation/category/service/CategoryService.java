/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.service;

import java.util.List;

import com.nts.reservation.category.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CategoryService {
	public List<Category> getItems();
}
