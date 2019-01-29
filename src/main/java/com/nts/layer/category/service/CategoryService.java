/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.service;

import com.nts.layer.category.dto.CategoryResponse;

/**
 * @author 전연빈
 */
public interface CategoryService {
	CategoryResponse selectCategories();
}
