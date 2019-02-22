/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.param.PageDto;

/**
 * 카테고리에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface CategoryService {
	List<CategoryDto> getCategoriesWithProductsOnDisplayCount(PageDto page);
}
