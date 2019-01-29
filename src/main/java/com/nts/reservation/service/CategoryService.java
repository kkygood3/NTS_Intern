/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Category;

/**
* FileName : CategoryService.java
* @author  : 이승수
* @date    : 2019. 1. 28.
*/
public interface CategoryService {

	List<Category> getCategories();

}
