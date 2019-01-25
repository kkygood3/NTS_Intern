/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.service;

import java.util.List;

import com.nts.layer.category.dto.Category;


/**
 * @author 전연빈
 */
public interface CategoryService {
	public List<Category> selectCategories();
}
