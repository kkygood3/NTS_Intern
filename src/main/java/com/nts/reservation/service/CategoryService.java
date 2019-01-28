/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.CategoryDto;

/*
 * @author 육성렬
 */
public interface CategoryService {
	public List<CategoryDto> getCategorys();
}
